package ModelEntrada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.scene.control.DatePicker;


public class EntradaDAO extends Entrada {
	public static final String GETBYID = "SELECT id,Descripcion,Fecha,FechaRecordatorio,id_a FROM entrada WHERE id=";
	private static final String DELETE = "DELETE FROM entrada WHERE id=?";
	private static final String INSERTUPDATE = "INSERT INTO entrada (id,Descripcion,Fecha,FechaRecordatorio,id_a) "
			+ "VALUES (?,?,?,?,?) " + "ON DUPLICATE KEY UPDATE Descripcion=?,Fecha=?,FechaRecordatorio=?,id_a=?";
	private final static String INSERT = "INSERT INTO entrada (id_a,Descripcion,Fecha,FechaRecordatorio) VALUES (?,?,?,?)";
	private static final String TODO = "SELECT * FROM entrada";
	private static final String GETBYCATEGORIA = "SELECT entrada,id,entrada.Descripcion,entrada.Fecha,entrada.FechaRecordatorio,entrada.id_a FROM entrada,asignatura WHERE id_a=entrada.id";
	private static final Statement ConnectionUtil = null;

	public EntradaDAO(String descripcionEntradaa, String descrip, int id) {
		super();
	}

	public EntradaDAO(int id, String Descripcion, String Fecha, String fechaRecordatorio, int id_a ) {
		super(id, Descripcion, Fecha, fechaRecordatorio, id_a);
	}
	public EntradaDAO(String Descripcion, String Fecha, String FechaRecordatorio, int id_a) {
		super(id_a, Descripcion, Fecha, FechaRecordatorio, id_a);
	
	}
	
	public EntradaDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public EntradaDAO(Entrada j) {
		this.id = j.id;
		this.Descripcion = j.Descripcion;
		this.Fecha = j.Fecha;
		this.FechaRecordatorio = j.FechaRecordatorio;
		this.id_a = j.id_a;
		
	}

	public EntradaDAO(int id, String descrip, String fecha, DatePicker fechaRecordatorio) {
		// TODO Auto-generated constructor stub
	}

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
