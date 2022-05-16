package ModelEntrada;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javafx.scene.control.TextField;


public class Entrada {
	int id;
	String Descripcion;
	String Fecha;
	String FechaRecordatorio ;
	int id_a;
	
	
	
	
	
	
	
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String a) {
		Fecha = a;
	}
	public String getFechaRecordatorio() {
		return FechaRecordatorio;
	}
	public void setFechaRecordatorio(String fechaRecordatorio) {
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
	
	

	
	
	public Entrada(int id, String descripcion, String fecha, String fechaRecordatorio, int id_a ) {
		super();
		this.id = id;
		Descripcion = descripcion;
		Fecha = fecha;
		FechaRecordatorio = fechaRecordatorio;
		this.id_a = id_a;
		
	}
	
	
	
	
	public Entrada(String descripcion, String fecha, String fechaRecordatorio, int id_a) {
		super();
		Descripcion = descripcion;
		Fecha = fecha;
		FechaRecordatorio = fechaRecordatorio;
		this.id_a = id_a;
		
	}
	 public Entrada() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Entrada [id=" + id + ", Descripcion=" + Descripcion + ", Fecha=" + Fecha + ", FechaRecordatorio="
				+ FechaRecordatorio + ", id_a=" + id_a + "]";
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	
}
