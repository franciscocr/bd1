
package dbexperimento;
import java.sql.*;


/**
 *
 * @author Francisc
 */
public class valores {
    conectate con;
    
    public valores (){
        con = new conectate();
        
    }
    
    public void Nuevovalores(String name, String Val1, String Val2, String mail){
        try{
            PreparedStatement pstm = con.getConnection().prepareStatement("insert into " +
                    "valores (nombre,valor1,valor2,mail)" +
                    "values(?,?,?,?)" );
                pstm.setString(1, name);
                pstm.setString(2, Val1);
                pstm.setString(3, Val2);
                pstm.setString(4, mail);
                pstm.execute();
            }catch(SQLException e){
            System.out.println(e);
        }
    }
        
    public void updatevalores(String id, String Nombre, String Valor1, String Valor2, String mail){
       try {            
            PreparedStatement pstm = con.getConnection().prepareStatement("update valores " +
            "set Nombre = ? ," +
            "Valor1 = ? ," +
            "Valor2 = ? ," +                    
            "mail = ? " +                    
            "where id = ? ");            
            pstm.setString(1, Nombre);
            pstm.setString(2, Valor1);
            pstm.setString(3, Valor2);
            pstm.setString(4, mail);
            pstm.setString(5, String.valueOf(id));
            pstm.execute();
            pstm.close();            
         }catch(SQLException e){
         System.out.println(e);
      }
   }
    
    public void deletevalores(String cod){  
            try {                
                PreparedStatement pstm = con.getConnection().prepareStatement("delete from valores "
                        + "where id = ?");            
                pstm.setString(1, cod);                   
                pstm.execute();
                pstm.close();            
            }catch(SQLException e){
            System.out.println(e);
            }            
   }
    
    public Object [][] getDatos(){
      int registros = 0;
      //obtenemos la cantidad de registros existentes en la tabla
      try{         
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(1) as total FROM valores ");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.out.println(e);
      }
      
    Object[][] data = new String[registros][5];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT " +
            " id, Nombre, Valor1, Valor2, mail " +
            " FROM valores" +
            " ORDER BY id ");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String estCodigo = res.getString("id");
            String estNombre = res.getString("nombre");
            String estValor1 = res.getString("Valor1");
            String estValor2 = res.getString("Valor2");
            String estmail = res.getString("mail");
            data[i][0] = estCodigo;            
            data[i][1] = estNombre;            
            data[i][2] = estValor1;            
            data[i][3] = estValor2;            
            data[i][4] = estmail;            
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }  
    
    
}
