package ModelAsignatura;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import ModelEntrada.Entrada;
import Utils.ConnectionUtil;

public class AsignaturaDAO extends Asignatura {
	/*
	 * Consulta para sacar el nombre y la id de una asignatura
	 */
	private final static String GETBYID = "SELECT id,nombre FROM asignatura WHERE id=";
	/*
	 * Consulta a la base de datos para actualizar o cambiar nombre de una
	 * asignatura
	 */
	private final static String INSERTUPDATE = "INSERT INTO asignatura (id, nombre)" + "VALUES (?,?) "
			+ "ON DUPLICATE KEY UPDATE nombre=?";
	/*
	 * Consulta a la base de datos para añadir una asignatura
	 */
	private final static String INSERT = "INSERT INTO asignatura (nombre) VALUES (?)";
	/*
	 * Consulta para eliminar una asignatura por su id
	 */
	private final static String DELETE = "DELETE FROM asignatura WHERE id=?";
	/*
	 * Consulta para mostrar Las asignaturas
	 */
	private final static String ASIGNATURA = "SELECT * FROM asignatura";
	/*
	 * Consulta para mostrar las entradas de una asignatura dependiendo de la id de
	 * la entrada
	 */
	private final static String ENTRADAS = "SELECT * FROM entrada WHERE id_a=";

	/**
	 * Constructor por defecto
	 */

	public AsignaturaDAO() {
		super();

	}

	/**
	 * Contructor por los siguientes parametros
	 * 
	 * @param id
	 * @param nombre
	 * @param Entradas
	 */
	public AsignaturaDAO(int id, String nombre, List<Entrada> Entradas) {
		super(id, nombre, Entradas);

	}

	/**
	 * Contructor con los siguientes parametros
	 * 
	 * @param id
	 * @param nombre
	 */
	public AsignaturaDAO(int id, String nombre) {
		super(id, nombre);
	}

	/**
	 * Contructor con los siguientes parametros
	 * 
	 * @param nombre
	 */
	public AsignaturaDAO(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}

	public AsignaturaDAO(Asignatura c) {
		this.id = c.getId();
		this.nombre = c.nombre;
		this.Entradas = c.Entradas;
	}

	/**
	 * Metodo con el cual nos conectamos a la base de datos 
	 * @param id
	 */
	public AsignaturaDAO(int id) {
		super();
		Connection con = ConnectionUtil.getConnection();
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = GETBYID + id;
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.nombre = rs.getString("nombre");
				}
				this.Entradas = AsignaturaDAO.buscarEntradaPorAsignatura(this.id);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	/*
	 * Metodo El cual añade una asignatura a la base de datos
	 */
	public int save() {
		int result = -1;

		try {
			Connection sql = ConnectionUtil.getConnection();

			if (this.id >= 0) {
				
				String nombre = this.nombre;
				String q = "UPDATE asignatura SET nombre =? WHERE id = " + id;
				PreparedStatement ps = sql.prepareStatement(q);
				ps.setString(1, nombre);
				result = ps.executeUpdate();

			} else {
				System.out.println(this.nombre);
				String nombre = this.nombre;
				String q = INSERT;
				PreparedStatement ps = sql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, nombre);
				result = ps.executeUpdate();
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						result = generatedKeys.getInt(1);
					}
				}
				this.id = result;

			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return result;
	}

	/**
	 * Metodo que nos muestra todas los asignaturas
	 * 
	 * @return
	 */
	public static List<Asignatura> GetAllAsignatura() {
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

	/**
	 * Metodo para buscar una entrada dependindo de la id de la asignatura
	 * 
	 * @param id
	 * @return
	 */
	public static List<Entrada> buscarEntradaPorAsignatura(int id) {
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
					añadir.setFecha(rs.getString("Fecha"));
					añadir.setFechaRecordatorio(rs.getString("FechaRecordatorio"));
					añadir.setId_a(rs.getInt("id_a"));

					Entradass.add(añadir);

				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return Entradass;
	}

	/**
	 * Metodo que devuelve la entradas que tiene una asignatura
	 * 
	 * @return
	 */
	public List<Entrada> getMiEntradas() {

		if (Entradas == null) {

			Entradas = buscarEntradaPorAsignatura(this.id);
		}
		return Entradas;
	}

	/**
	 * Metodo para eliminar una asignatura de la base de datos
	 * 
	 * @return
	 */
	public int eliminar() {
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
