package es.HNOS.HNOS;

import java.util.ArrayList;
import java.util.List;

import ModelAsignatura.Asignatura;
import ModelAsignatura.AsignaturaDAO;
import ModelEntrada.Entrada;


public class AppController {
	
	public static int idAsignatura=0;
	
	public static List<Asignatura> todas = AsignaturaDAO.GetAllAsignatura();

	public static List<Entrada> Entradas = new ArrayList<Entrada>();
	

}
