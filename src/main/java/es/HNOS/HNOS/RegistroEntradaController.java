package es.HNOS.HNOS;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import ModelAsignatura.Asignatura;
import ModelAsignatura.AsignaturaDAO;
import ModelEntrada.EntradaDAO;

import java.util.List;
import ModelEntrada.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ChoiceBoxSkin;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class RegistroEntradaController {


	ObservableList<Asignatura> options = FXCollections.observableArrayList(AsignaturaDAO.GetAllAsignatura());
	/*
	 * DatePicker el cual guarda FechaRecordatorio
	 */
	@FXML
	private DatePicker FechaRecordatorio;
	/*
	 * DatePicker el cual guarda Fecha
	 */
	@FXML
	private DatePicker Fecha;
/*
 * Boton agregar
 */
	@FXML
	private Button Agregar;
/*
 * TextField el cual guarda la descripcion de cada entrada
 */
	@FXML
	private TextField DescripcionEntrada;
/*
 * TextField el cual guarda el nombre de a asingatura de la que va a ser la entrada
 */
	@FXML
	private TextField DescripcionAsignatura;
	/*
	 * ChoiceBox para eleguir a que asignatura va a ser
	 */
	@FXML
	private ChoiceBox<String> c;

	public static EntradaDAO Entrada;

	List<Asignatura> lista = AsignaturaDAO.GetAllAsignatura();
/*
 * Metodo para iniciar 
 */
	@FXML
	private void initialize() {
		for (Asignatura e : lista) {
			c.getItems().add(e.getNombre());
		}

	}
/*
 * Metodo asignado a un boton para crear una entrada 
 */
	@FXML
	private void CrearEntrada() {

		if (Entrada.getId() >= 0) {
			 
			String DescripcionEntradaa = DescripcionEntrada.getText();
			String fecha = Fecha.getValue().toString();
			String FechaRecordatorioo = FechaRecordatorio.getValue().toString();
			String nombre = c.getSelectionModel().getSelectedItem();
			int id = 0;
			for (Asignatura e : lista) {
				if (e.getNombre().equals(nombre)) {
					id = e.getId();
				}
			}

			Entrada.setDescripcion(DescripcionEntradaa);
			Entrada.setId_a(id);
			Entrada.setFecha(fecha);
			Entrada.setFechaRecordatorio(FechaRecordatorioo);
			Entrada.save();
		} else {

			
			String Descrip = DescripcionAsignatura.getText();
			String fecha = Fecha.getValue().toString();
			String FechaRecordatorioo = FechaRecordatorio.getValue().toString();
			String nombre = c.getSelectionModel().getSelectedItem();
			int id = 0;
			for (Asignatura e : lista) {
				if (e.getNombre().equals(nombre)) {
					id = e.getId();
				}
			}
			EntradaDAO j = new EntradaDAO( Descrip, fecha, FechaRecordatorioo,id );
			j.save();
			if (j.getId_a() == AppController.idAsignatura) {
				AppController.Entradas.add(j);
				
				
			}
		}
		
		App.closeScene((Stage) Agregar.getScene().getWindow());

	}

	public static Entrada getEntrada() {
		return Entrada;
	}

	public static void setEntrada(EntradaDAO entrada) {
		RegistroEntradaController.Entrada = entrada;
	}

}
