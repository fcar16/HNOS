package es.HNOS.HNOS;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ModelAsignatura.Asignatura;
import ModelAsignatura.AsignaturaDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class InicioController {

	/*
	 * Label que muestra la id de la asignatura
	 */
	@FXML
	private Label idLabel;
/*
 * Label que muestra el nombre de la asignatura
 */
	@FXML
	private Label nombreLabel;
/*
 * Tabla que muestra informacion de la asignatura selecionada
 */
	@FXML
	TableView<Asignatura> TablaAsignatura;
/*
 * Columna que muestra la id de la asignatura selecionada
 */
	@FXML
	private TableColumn<Asignatura, String> idColumna;
	/*
	 * Columna que muestra el nombre de la asignatura selecionada
	 */
	@FXML
	private TableColumn<Asignatura, String> nombreColumna;
/*
 * Metodo por el cual carga la tabla y muestra la informacion de todo
 */
	@FXML
	protected void initialize() {
		System.out.println("Cargando...");
		muestraInfo(null);
		configuraTabla();

		TablaAsignatura.setItems(FXCollections.observableArrayList(AppController.todas));
		TablaAsignatura.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				muestraInfo(newValue);
			});

	}
/*
 * Metodo el cual configura la tabla con la informacion de la base de datos
 */
	private void configuraTabla() {
		//Columna De ID de la Tabla
		idColumna.setCellValueFactory(cadaAsignatura -> {
			SimpleStringProperty c = new SimpleStringProperty();
			c.setValue(Integer.toString(cadaAsignatura.getValue().getId()));
			return c;
		});
		//Columna De Nombre de la Tabla
		nombreColumna.setCellValueFactory(cadaAsignatura -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadaAsignatura.getValue().getNombre());
			return v;
		});
	}
/**
 * Metodo que nos muestra la informacion de una asignatura
 * @param p Asignatura que le pasamos al metodo para que se nos muestre su información
 */
	private void muestraInfo(Asignatura p) {

		if (p != null) {
			idLabel.setText(Integer.toString(p.getId()));

			nombreLabel.setText(p.getNombre());
		} else {
			idLabel.setText("");
			nombreLabel.setText("");
		}
	}
/**
 * Metodo para eliminar una asignatura por su clave primaria que seria ID
 */
	@FXML
	private void EliminarAsignatura() {
		int id = Integer.parseInt(idLabel.getText());
		if (id >= 0) {
			AsignaturaDAO p = new AsignaturaDAO(id);
			mostrarAlertWarning();
			p.eliminar();
			AppController.todas.remove(p);
			System.out.println("ELiminado");
			AppController.todas = AsignaturaDAO.GetAllAsignatura();
			try {
				App.setRoot("Inicio");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
/**
 * Metodo el cual nos muestra una Ventana Emergente cuando se elimina una asignatura
 */
	@FXML
	private void mostrarAlertWarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Eliminar Asignatura");
		alert.setContentText("Se va a Eliminar Una Asignatura de la base de datos");
		alert.showAndWait();
	}
/**
 * Metodo Que nos muestra las entradas de una asignatura  asignado al boton mostrar entrada
 */
	@FXML
	private void MostrarEntrada() {
		int id = Integer.parseInt(idLabel.getText());
		Stage p = new Stage();
		AsignaturaDAO s = new AsignaturaDAO(id);

		AppController.Entradas = s.getEntradas();
		AppController.idAsignatura = s.getId();
		System.out.println(AppController.Entradas);
		try {
			App.loadScene(p, "InicioEntradas", "Entradas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * Metodo Que nos sirve para Editar una Asignatura por ID asignado al boton de editar
 */
	@FXML
	private void EditarAsignatura() {
		int id = Integer.parseInt(idLabel.getText());
		if (id >= 0) {
			AsignaturaDAO c = new AsignaturaDAO(id);
			RegistroController.setAsignatura(c);
			try {
				App.loadScene(new Stage(), "registro", "Actualizar");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Asignatura j : AppController.todas) {
				if (j.getId() == c.getId()) {
					j.setNombre(c.getNombre());

				}
			}
			TablaAsignatura.refresh();
		}
	}
/*
 * Metodo el cual añade una asignatura asignado al boton añadir asignatura
 */
	@FXML
	private void AñadirAsignatura() {
		Stage p = new Stage();
		AsignaturaDAO c = new AsignaturaDAO();
		System.out.println();
		RegistroController.setAsignatura(c);
		try {
			App.loadScene(p, "registro", "Añadir Asignatura");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
