package Utils;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    
    private static void compruebaestructura(java.sql.Connection conn) {
        String tabla1, tabla2;
        tabla1="CREATE TABLE IF NOT EXISTS asignatura(" +
                "id int(11) NOT NULL," +
                "nombre text NOT NULL" +
                ");";
        tabla2="CREATE TABLE IF NOT EXISTS entrada(" +
                "id int(11) NOT NULL," +
                "Descripcion text DEFAULT NULL," +
                "Fecha Date NOT NULL," +
                "FechaRecordatorio Date NOT NULL," +
                "id_a int(11) NOT NULL," +
                "CONSTRAINT entrada_asignatura FOREIGN KEY (id_a) REFERENCES asignatura (id)" +
                "ON DELETE CASCADE ON UPDATE CASCADE" +
                ");";
        try {
            PreparedStatement t1 = conn.prepareStatement(tabla1);
            PreparedStatement t2 = conn.prepareStatement(tabla2);
            t1.executeUpdate();
            t2.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
