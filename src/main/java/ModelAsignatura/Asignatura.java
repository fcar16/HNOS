package ModelAsignatura;

import java.util.ArrayList;
import java.util.List;

import ModelEntrada.Entrada;



public class Asignatura {
	protected int id;
	protected String nombre;
	protected List<Entrada> Entradas;

	
	
/**
 * Contructor por defecto
 */
	public Asignatura() {
		this(-1,"");
	}

	/**
	 * Contructor por los siguientes parametros
	 * @param id
	 * @param nombre
	 * @param Entradas
	 */
	public Asignatura(int id, String nombre, List<Entrada> Entradas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.Entradas = Entradas;
	}
	/**
	 * Contructor con el siguiente parametro
	 * @param nombre
	 */
	public Asignatura(String nombre) {
		super();
		this.id = -1;
		this.nombre = nombre;
		this.Entradas = Entradas;
	} 

	/**
	 * Contrurcto con los siguientes parametros
	 * @param id
	 * @param nombre
	 */
	public Asignatura(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		Entradas=new ArrayList<>();
	}
/*
 * Metodo Para sacar la id
 */
	public int getId() {
		return id;
	}
/**
 * Metodo para cambiar la variable
 * @param id
 */
	public void setId(int id) {
		this.id = id;
	}
/*
 * Metodo para sacar el nombre
 */
	public String getNombre() {
		return nombre;
	}
/**
 * Metodo para cambiar el nombre de la asignatura por otro que le pasas
 * @param nombre que le pasas por el cual vas a cambiar
 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
/*
 * Metodo para ver  las entradas que hay
 */
	public List<Entrada> getEntradas() {
		return Entradas;
	}

/*
 * Metodo para cambiar o añadir entradas a la lista
 */
	public void setEntradas(List<Entrada> Entradas) {
		this.Entradas = Entradas;
	}


	@Override
	public String toString() {
		return "Asignatura [id=" + id + ", nombre=" + nombre + "]";
	}


}
