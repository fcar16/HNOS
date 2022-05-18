package Interfaces;

import java.util.List;

import ModelAsignatura.Asignatura;
import ModelEntrada.Entrada;

public interface AsignaturaDao {
	public static final int save = 0;
	public static List<Asignatura> GetAllAsignatura() {
		return null;
	}
	public static List<Entrada> buscarEntradaPorAsignatura(int id){
		return null;
	}
	public List<Entrada> getMiEntradas();
	public int eliminar();
}
