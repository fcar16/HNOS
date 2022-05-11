package ModelEntrada;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javafx.scene.control.TextField;


public class Entrada {
	int id;
	
	String Descripcion;
	Date Fecha;
	Date FechaRecordatorio ;
	int id_a;
	
	Boolean Estado;
	
	
	
	
	
	
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date localDate) {
		Fecha = localDate;
	}
	public Date getFechaRecordatorio() {
		return FechaRecordatorio;
	}
	public void setFechaRecordatorio(Date fechaRecordatorio) {
		FechaRecordatorio = fechaRecordatorio;
	}
	public int getId_a() {
		return id_a;
	}
	public void setId_a(int id_a) {
		this.id_a = id_a;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	

	
	
	public Entrada(int id, String descripcion, Date fecha, Date fechaRecordatorio, int id_a, Boolean estado) {
		super();
		this.id = id;
		Descripcion = descripcion;
		Fecha = fecha;
		FechaRecordatorio = fechaRecordatorio;
		this.id_a = id_a;
		Estado = estado;
	}
	
	
	
	
	public Entrada(String descripcion, Date fecha, Date fechaRecordatorio, int id_a, Boolean estado) {
		super();
		Descripcion = descripcion;
		Fecha = fecha;
		FechaRecordatorio = fechaRecordatorio;
		this.id_a = id_a;
		Estado = estado;
	}
	 public Entrada() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(Descripcion, Estado, Fecha, FechaRecordatorio, id, id_a);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrada other = (Entrada) obj;
		return Objects.equals(Descripcion, other.Descripcion) && Objects.equals(Estado, other.Estado)
				&& Objects.equals(Fecha, other.Fecha) && Objects.equals(FechaRecordatorio, other.FechaRecordatorio)
				&& id == other.id && id_a == other.id_a;
	}
	@Override
	public String toString() {
		return "Entrada [Descripcion=" + Descripcion + ", Fecha=" + Fecha + ", FechaRecordatorio=" + FechaRecordatorio
				+ ", id_a=" + id_a + ", id=" + id + ", Estado=" + Estado + "]";
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
}
