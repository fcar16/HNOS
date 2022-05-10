package ModelAsignatura;

import java.util.ArrayList;
import java.util.List;

import ModelEntrada.Entrada;



public class Asignatura {
	protected int id=0;
	protected String nombre;
	protected List<Entrada> Entradas;

	
	

	public Asignatura() {
		this(-1,"");
	}

	
	public Asignatura(int id, String nombre, List<Entrada> Entradas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.Entradas = Entradas;
	}
	public Asignatura(String nombre) {
		super();
		this.id = -1;
		this.nombre = nombre;
		this.Entradas = Entradas;
	} 

	
	public Asignatura(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		Entradas=new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Entrada> getEntradas() {
		return Entradas;
	}


	public void setEntradas(List<Entrada> Entradas) {
		this.Entradas = Entradas;
	}


	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}


}
