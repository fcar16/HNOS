package ModelEntrada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EntradaDAO extends Entrada {
	public static final String GETBYID = "SELECT id,Descripcion,Fecha,FechaRecordatorio,id_a FROM entrada WHERE id=";
	private static final String DELETE = "DELETE FROM entrada WHERE id=?";
	private static final String INSERTUPDATE = "INSERT INTO entrada (id, Descripcion,Fecha,FechRecordatorio,id_a) "
			+ "VALUES (?,?,?,?,?) " + "ON DUPLICATE KEY UPDATE Descripcion=?,Fecha=?,FechaRecordatorio=?,id_a=?";
	private final static String INSERT = "INSERT INTO entrada (id,Descripcion,Fecha,id_a) VALUES (?,?,?,?)";
	private static final String TODO = "SELECT * FROM entrada";
	private static final String GETBYCATEGORIA = "SELECT entrada,id,entrada.Descripcion,entrada.Fecha,entrada.FechaRecordatorio,entrada.id_a FROM entrada,asignatura WHERE id_a=entrada.id";
	private static final Statement ConnectionUtil = null;

	public EntradaDAO(String descripcionEntradaa, String descrip, int id) {
		super();
	}

	public EntradaDAO(int id, String Descripcion, Date Fecha, Date FechaRecordatorio, int id_a ,Boolean Estado) {
		super(id, Descripcion, Fecha, FechaRecordatorio, id_a, Estado);
	}
	public EntradaDAO(String Descripcion, Date Fecha, Date FechaRecordatorio, int id_a, Boolean Estado) {
		super(id_a, Descripcion, Fecha, FechaRecordatorio, id_a, Estado);
	
	}
	
	public EntradaDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EntradaDAO(int id)  {
		super();
		Connection con = (Connection) Utils.ConnectionUtil.getConnection();
		// Stament
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = GETBYID + id;
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.Descripcion = rs.getString("descripcion")	;
					this.Fecha = rs.getDate("Fecha");
					this.FechaRecordatorio = rs.getDate("FechaRecordatorio");
					this.id_a = rs.getInt("id_a");
					this.Estado = rs.getBoolean("Estado");
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
		this.Estado =j.Estado;
	}

	public int save(){
        int result = -1;
        
        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            
            if(this.id>0){
                //UPDATE
            	String Descripcion=this.Descripcion;
                String q = "UPDATE entrada SET Descripcion=?,Fecha=?,FechaRecordatorio=?,id_a=? , Estado=? WHERE id ="+id;
                PreparedStatement ps = csql.prepareStatement(q);
                ps.setString(1, Descripcion);
                ps.setDate(2, (java.sql.Date) Fecha);
                ps.setDate(3, (java.sql.Date) FechaRecordatorio);
                ps.setInt(4, id_a);
                ps.setBoolean(5, Estado);
                result= ps.executeUpdate();
                
            }else {
                //INSERT
                String q = INSERT;
                PreparedStatement ps = csql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
                
                
                ps.setString(1, this.Descripcion);
                ps.setDate(2, (java.sql.Date) this.Fecha);
                ps.setDate(3, (java.sql.Date) this.FechaRecordatorio);
                ps.setInt(4, this.id_a);
                ps.setBoolean(5, this.Estado);
                result = ps.executeUpdate();
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        result = generatedKeys.getInt(1); //devuelve el ultimo id insertado
                    }
                }
                this.id = result;
            }
            
        }catch (SQLException ex) {
            System.out.println("Error Guardando categoria");
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

	public static List<Entrada> GetAllJuego() throws SQLException {
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
