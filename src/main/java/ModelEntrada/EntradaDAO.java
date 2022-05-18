package ModelEntrada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;

import javafx.scene.control.DatePicker;


public class EntradaDAO extends Entrada {
	/**
	 * Consulta la cual nos da la entrada dependiendo de la id
	 */
	public static final String GETBYID = "SELECT id,Descripcion,Fecha,FechaRecordatorio,id_a FROM entrada WHERE id=";
	/**
	 * Consulta para eliminar una entrada por id
	 */
	private static final String DELETE = "DELETE FROM entrada WHERE id=?";
	/**
	 * Consulta para añadir una entrada
	 */
	private final static String INSERT = "INSERT INTO entrada (id_a,Descripcion,Fecha,FechaRecordatorio) VALUES (?,?,?,?)";
	/**
	 * Consulta para que nos de todas las entradas
	 */
	private static final String TODO = "SELECT * FROM entrada";
	private static final Statement ConnectionUtil = null;

	/**
	 * Contructor por los siguientes parametros
	 * @param descripcionEntradaa
	 * @param descrip
	 * @param id
	 */
	public EntradaDAO(String descripcionEntradaa, String descrip, int id) {
		super();
	}

	/**
	 *  Contructor por los siguientes parametros
	 * @param id
	 * @param Descripcion
	 * @param Fecha
	 * @param fechaRecordatorio
	 * @param id_a
	 */
	public EntradaDAO(int id, String Descripcion, String Fecha, String fechaRecordatorio, int id_a ) {
		super(id, Descripcion, Fecha, fechaRecordatorio, id_a);
	}
	/**
	 *  Contructor por los siguientes parametros
	 * @param Descripcion
	 * @param Fecha
	 * @param FechaRecordatorio
	 * @param id_a
	 */
	public EntradaDAO(String Descripcion, String Fecha, String FechaRecordatorio, int id_a) {
		super(id_a, Descripcion, Fecha, FechaRecordatorio, id_a);
	
	}
	
	/**
	 * Contructor por defecto
	 */
	public EntradaDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
/**
 * Metodo con el que nos conectamos a la base de datos 
 * @param id
 */
	public EntradaDAO(int id)  {
		super();
		Connection con = (Connection) Utils.ConnectionUtil.getConnection();
	
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = GETBYID + id;
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.Descripcion = rs.getString("descripcion")	;
					this.Fecha =  rs.getString("Fecha");
					this.FechaRecordatorio =  rs.getString("FechaRecordatorio");
					this.id_a = rs.getInt("id_a");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
/**
 * Contructor por defecto
 * @param j entrada que se le pasa
 */
	public EntradaDAO(Entrada j) {
		this.id = j.id;
		this.Descripcion = j.Descripcion;
		this.Fecha = j.Fecha;
		this.FechaRecordatorio = j.FechaRecordatorio;
		this.id_a = j.id_a;
		
	}
/**
 * Contructor con los siguientes parametros
 * @param id
 * @param descrip
 * @param fecha
 * @param fechaRecordatorio
 */
	public EntradaDAO(int id, String descrip, String fecha, DatePicker fechaRecordatorio) {
		// TODO Auto-generated constructor stub
	}
/**
 * Metodo el cual si entra en el if nos sirve para editar una entrada, si entra en el else crea una y la inserta en la base de datos
 * @return
 */
	public int save(){
        int result = -1;
        
        try {
            java.sql.Connection sql = Utils.ConnectionUtil.getConnection();
            
            if(this.id>0){
                
            	String Descripcion=this.Descripcion;
                String q = "UPDATE entrada SET id_a=?,Descripcion=?,Fecha=?,FechaRecordatorio=? , id=? WHERE id ="+id;
                PreparedStatement ps = sql.prepareStatement(q); 
                ps.setInt(1, id_a);
                ps.setString(2, Descripcion);
                ps.setObject(3,  Fecha);
                ps.setObject(4,  FechaRecordatorio);
                ps.setInt(5, id);
               
                result= ps.executeUpdate();
                
            }else {
                
                String q = INSERT;
                PreparedStatement ps = sql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, this.id_a);
                ps.setString(2, this.Descripcion);
                ps.setObject(3,  this.Fecha);
                ps.setObject(4,  this.FechaRecordatorio);
                
               
          
                result = ps.executeUpdate();
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        result = generatedKeys.getInt(1);
                    }
                }
                this.id = result;
            }
            
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return result;
    }
/**
 * Metodo para eliminar una entrada de la base de datos
 * @return
 */
	public int eliminar()  {
		int rs=0;
		Connection con = Utils.ConnectionUtil.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(DELETE);
				q.setInt(1, this.id);
				rs =q.executeUpdate();
				this.id=-1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;

	}
/**
 * Metodo para conseguir todas las entradas que tenemos en la base de datos
 * @return Devuelve una lista con todas las entradas
 * @throws SQLException
 */
	public static List<Entrada> GetAllEntradas() throws SQLException {
		List<Entrada> Base = new ArrayList<Entrada>();
		Connection con = ConnectionUtil.getConnection();

		if (con != null) {
			try {

				Statement st = con.createStatement();
				String q = TODO;
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					Entrada base = new Entrada();
					int id = rs.getInt("id");
					base.setId(rs.getInt("id"));
					base.setDescripcion(rs.getString("Descripcion"));
					Base.add(base);
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return Base;
	}

}
