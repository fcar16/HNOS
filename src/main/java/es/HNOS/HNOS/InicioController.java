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

	@FXML
	private Label idLabel;

	@FXML
	private Label nombreLabel;

	@FXML
	TableView<Asignatura> tablaAsignatura;

	@FXML
	private TableColumn<Asignatura, String> idColumna;
	@FXML
	private TableColumn<Asignatura, String> nombreColumna;

	@FXML
	protected void initialize() {
		System.out.println("Cargando...");
		muestraInfo(null);
		configuraTabla();
		

		/*tablaAsignatura.setItems(FXCollections.observableArrayList(AppController.todas));
		tablaAsignatura.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			muestraInfo(newValue);
		});*/
		
	}

	private void configuraTabla() {
		idColumna.setCellValueFactory(cadaAsignatura -> {
			SimpleStringProperty c = new SimpleStringProperty();
			c.setValue(Integer.toString(cadaAsignatura.getValue().getId()));
			return c;
		});
		nombreColumna.setCellValueFactory(cadaAsignatura -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadaAsignatura.getValue().getNombre());
			return v;
		});
	}

	private void muestraInfo(Asignatura p) {

		if (p != null) {
			idLabel.setText(Integer.toString(p.getId()));
			
			nombreLabel.setText(p.getNombre());
		} else {
			idLabel.setText("");
			nombreLabel.setText("");
		}
	}

	@FXML
	private void EliminarAsignatura()  {
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

	@FXML
	private void mostrarAlertWarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Eliminar Asignatura");
		alert.setContentText("Se va a Eliminar Una Asignatura de la base de datos");
		alert.showAndWait();
	}

	@FXML
	private void MostrarEntrada()  {
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
			tablaAsignatura.refresh();

		}
	}

	@FXML
	private void AñadirAsignatura() {
		Stage p = new Stage();
		AsignaturaDAO c=new AsignaturaDAO();
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
