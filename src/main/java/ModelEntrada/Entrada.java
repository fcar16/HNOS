package ModelEntrada;

import java.util.Objects;



public class Entrada {
	int id;
	String Descripcion;
	String Fecha;
	String FechaRecordatorio ;
	int id_a;
	
	
	
	
	
	
	/**
	 * Metodo para conseguir  la descripcion  de una entrada
	 * @return devuelve la descripcion de la entrada
	 */
	public String getDescripcion() {
		return Descripcion;
	}
	/**
	 * Metodo para setear la descripcion por otra descripcion de una entrada
	 * @param descripcion por la que cambias 
	 */
	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}
	/**
	 * Metodo para conseguir la fecha de una entrada
	 * @return devielve la fecha
	 */
	public String getFecha() {
		return Fecha;
	}
	/**
	 * Metodo para setear la fecha por otra fecha de una entrada
	 * @param fecha por la que cambias 
	 */
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	/**
	 * Metodo para conseguir la fecha recordatorio de una entrada
	 * @return devuelve la fecharecordatorio
	 */
	public String getFechaRecordatorio() {
		return FechaRecordatorio;
	}
	/**
	 * Metodo para setear la fecha recordatorio por otra fecha recordatorio
	 * @param fechaRecordatorio por la que cambiar
	 */
	public void setFechaRecordatorio(String fechaRecordatorio) {
		FechaRecordatorio = fechaRecordatorio;
	}
	/**
	 * Metodo para conseguir la id_a es decir la id de la asignatura asignada
	 * @return devuelve la id de la asignatura
	 */
	public int getId_a() {
		return id_a;
	}
	/**
	 * Metodo para setear la id_a por otra
	 * @param id_a por la que cambiar
	 */
	public void setId_a(int id_a) {
		this.id_a = id_a;
	}
	/**
	 * Metodo para conseguir la id de la entrada
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * Metodo para setear la id por otra
	 * @param id por la que cambias
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	

	
	/**
	 * Contructor con los siguientes parametros
	 * @param id
	 * @param descripcion
	 * @param fecha
	 * @param fechaRecordatorio
	 * @param id_a
	 */
	public Entrada(int id, String descripcion, String fecha, String fechaRecordatorio, int id_a ) {
		super();
		this.id = id;
		Descripcion = descripcion;
		Fecha = fecha;
		FechaRecordatorio = fechaRecordatorio;
		this.id_a = id_a;
		
	}
	
	
	
	/**
	 * Contructor con los siguientess parametros
	 * @param descripcion
	 * @param fecha
	 * @param fechaRecordatorio
	 * @param id_a
	 */
	public Entrada(String descripcion, String fecha, String fechaRecordatorio, int id_a) {
		super();
		Descripcion = descripcion;
		Fecha = fecha;
		FechaRecordatorio = fechaRecordatorio;
		this.id_a = id_a;
		
	}
	/**
	 * Contructor por defecto
	 */
	 public Entrada() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	/**
	 * Metodo que comparan si dos entradas son iguales por la id
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrada other = (Entrada) obj;
		return id == other.id;
	}
	/**
	 * Metodo que escribe toda la informacion de una entrada
	 */
	@Override
	public String toString() {
		return "Entrada [id=" + id + ", Descripcion=" + Descripcion + ", Fecha=" + Fecha + ", FechaRecordatorio="
				+ FechaRecordatorio + ", id_a=" + id_a + "]";
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	
}
