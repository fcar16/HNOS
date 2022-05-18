package Interfaces;

import java.util.List;

import ModelEntrada.Entrada;

public interface EntradaDAO {
	public int save();
	public int eliminar();
	public static List<Entrada> GetAllEntradas() {
		return null;
	}
	
}
