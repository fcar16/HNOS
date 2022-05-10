package es.HNOS.HNOS;

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
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ChoiceBoxSkin;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class RegistroEntradaController {

	private static final Labeled Descripcion = null;

	ObservableList<Asignatura> options = FXCollections.observableArrayList(AsignaturaDAO.GetAllAsignatura());

	@FXML
	private Button Agregar;

	@FXML
	private TextField DescripcionEntrada;

	@FXML
	private TextField DescripcionAsignatura;
	@FXML
	private ChoiceBox<String> c;

	public static EntradaDAO Entrada;

	List<Asignatura> lista = AsignaturaDAO.GetAllAsignatura();

	@FXML
	private void initialize() {
		for (Asignatura e : lista) {
			c.getItems().add(e.getNombre());
		}

	}

	@FXML
	private void CrearEntrada() {

		if (Entrada.getId() >= 0) {

			String DescripcionEntradaa = DescripcionEntrada.getText();
			String Descrip = DescripcionAsignatura.getText();
			String nombre = c.getSelectionModel().getSelectedItem();
			int id = 0;
			for (Asignatura e : lista) {
				if (e.getNombre().equals(nombre)) {
					id = e.getId();
				}
			}

			Entrada.setDescripcion(DescripcionEntradaa);
			Entrada.setId_a(id);
			Entrada.save();
		} else {

			String DescripcionEntradaa = DescripcionEntrada.getText();
			String Descrip = DescripcionAsignatura.getText();
			String nombre = c.getSelectionModel().getSelectedItem();
			int id = 0;
			for (Asignatura e : lista) {
				if (e.getNombre().equals(nombre)) {
					id = e.getId();
				}
			}
			EntradaDAO j = new EntradaDAO(DescripcionEntradaa, Descrip, id);
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
