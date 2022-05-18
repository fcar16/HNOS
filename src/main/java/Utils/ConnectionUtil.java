package Utils;



import java.sql.DriverManager;

import java.sql.SQLException;
import Connection.Connection;







public class ConnectionUtil  {
private static java.sql.Connection con=null;
	
public static java.sql.Connection connect(Connection c) throws ClassNotFoundException, SQLException{
    java.sql.Connection conn = null;
    
    if(c==null){
        conn = null;
    }else{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn=DriverManager.getConnection("jdbc:mysql://"+c.getHost()+"/"+c.getDb()+
                "?useLegacyDatetimeCode=false&serverTimezone=UTC",c.getUsuario(),c.getContraseña());
    
    }
    
    return conn; 
}
    public static java.sql.Connection getConnection(){
        if(con == null){
            Connection c = new Connection();
            c.loadDataXML();
            try {
                con=connect(c);
            } catch (ClassNotFoundException ex) {
            	
            } catch (SQLException ex) {
                System.out.println("Falta la base de datos");
                System.exit(0);
            }
        }
        return con;
    }
    
    public static void closeConnection(){
        if(con!=null){
            try {
            	con.close();
            } catch (SQLException ex) {
                System.out.println("Error en la conexion");
            }
        }
    }
    
  

}
