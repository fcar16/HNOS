package ModelAsignatura;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import ModelEntrada.Entrada;
import Utils.ConnectionUtil;
	
public class AsignaturaDAO extends Asignatura {

	private final static String GETBYID = "SELECT id,nombre FROM asignatura WHERE id=";
	private final static String INSERTUPDATE = "INSERT INTO asignatura (id, nombre)" + "VALUES (?,?) "
			+ "ON DUPLICATE KEY UPDATE nombre=?";
	private final static String INSERT = "INSERT INTO asignatura (nombre) VALUES (?)";
	private final static String DELETE = "DELETE FROM asignatura WHERE id=?";

	private final static String ENTRADAs = "SELECT * FROM entrada,asignatura WHERE id_a=a.id";
	private final static String ASIGNATURA = "SELECT * FROM asignatura";

	private final static String ENTRADAS = "SELECT * FROM entrada WHERE id_a=";

	public AsignaturaDAO() {
		super();

	}

	public AsignaturaDAO(int id, String nombre, List<Entrada> Entradas) {
		super(id, nombre, Entradas);

	}

	public AsignaturaDAO(int id, String nombre) {
		super(id, nombre);
	}

	public AsignaturaDAO(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}

	public AsignaturaDAO(Asignatura c) {
		this.id = c.getId();
		this.nombre = c.nombre;
		this.Entradas = c.Entradas;
	}

	
	public AsignaturaDAO(int id)  {
		super();
		Connection con = (Connection) ConnectionUtil.getConnection();
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = GETBYID + id;
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.nombre = rs.getString("nombre");
				}
				this.Entradas=AsignaturaDAO.buscarEntradaPorAsignatura(this.id);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public int save() {
		int result = -1;

		try {
			java.sql.Connection sql = ConnectionUtil.getConnection();

			if (this.id >= 0) {
				String nombre = this.nombre;
				String q = "UPDATE asignatura SET nombre =? WHERE id = " + id;
				PreparedStatement ps = sql.prepareStatement(q);
				ps.setString(1, nombre);
				result = ps.executeUpdate();

			} else {
				
				String q = INSERT;
				PreparedStatement ps = sql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, this.nombre);
				result = ps.executeUpdate();
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						result = generatedKeys.getInt(1); 
					}
				}
				this.id = result;
			}

		} catch (SQLException ex) {
			System.out.println("Error Guardando Asignatura");
		}

		return result;
	}

	public static List<Asignatura> GetAllAsignatura()   {
		List<Asignatura> Base = new ArrayList<Asignatura>();
		Connection con = ConnectionUtil.getConnection();

		if (con != null) {
			try {

				Statement st = con.createStatement();
				String q = ASIGNATURA;
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					Asignatura base = new Asignatura();
					int id = rs.getInt("id");
					base.setId(rs.getInt("id"));
					base.setNombre(rs.getString("nombre"));
					if (buscarEntradaPorAsignatura(id) == null) {
						base.setEntradas(new ArrayList<Entrada>());
					} else {
						base.setEntradas(buscarEntradaPorAsignatura(id));
					}
					Base.add(base);
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return Base;
	}

	public static List<Entrada> buscarEntradaPorAsignatura(int id) throws SQLException {
		List<Entrada> Entradass = new ArrayList<Entrada>();
		Connection con = ConnectionUtil.getConnection();
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = ENTRADAS + id;
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					Entrada añadir = new Entrada();
					añadir.setId(rs.getInt("id"));
					añadir.setDescripcion(rs.getString("Descripcion"));
					añadir.setFecha((Date) rs.getObject("Fecha"));
					añadir.setFechaRecordatorio((Date) (rs.getObject("FechaRecordatorio")));
					añadir.setId_a(rs.getInt("id_a"));
					añadir.setEstado(rs.getBoolean("Estado"));
					Entradass.add(añadir);

				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return Entradass;
	}

	public List<Entrada> getMiEntradas() throws SQLException {

		
		if (Entradas == null) {
			
			Entradas = buscarEntradaPorAsignatura(this.id);
		}
		return Entradas;
	}

	public int eliminar()  {
		int rs = 0;
		Connection con = ConnectionUtil.getConnection();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETE);
				q.setInt(1, this.id);
				rs = q.executeUpdate();
				this.id = -1;
				this.nombre = "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

}
