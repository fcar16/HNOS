package es.HNOS.HNOS;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import javax.swing.ImageIcon;

import ModelEntrada.Entrada;
import ModelEntrada.EntradaDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EntradaController {
	
	@FXML
	private DatePicker FechaRecordatorio;
	
	
	@FXML
	private DatePicker Fecha;

	@FXML
	private Label idLabel;

	@FXML
	private Label nombreLabel;

	@FXML
	private Button Eliminar;
	
	@FXML
	TableView<Entrada> TablaEntrada;

	@FXML
	private TableColumn<Entrada, String> idColumna;
	@FXML
	private TableColumn<Entrada, String> nombreColumna;

	@FXML
	protected void Initialize() {
		System.out.println("Cargando...");

		muestraInfo(null);
		configuraTabla();
		
		TablaEntrada.setItems(FXCollections.observableArrayList(AppController.Entradas));
		TablaEntrada.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			muestraInfo(newValue);
		});
	}

	private void configuraTabla() {
		idColumna.setCellValueFactory(cadaAsignatura -> {
			SimpleStringProperty c = new SimpleStringProperty();
			c.setValue(Integer.toString(cadaAsignatura.getValue().getId()));
			return c;
		});
		nombreColumna.setCellValueFactory(cadaAsignatura -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadaAsignatura.getValue().getDescripcion());
			return v;
		});
	}

	private void muestraInfo(Entrada p) {

		if (p != null) {
			idLabel.setText(Integer.toString(p.getId()));
				
			nombreLabel.setText(p.getDescripcion());
			//Fecha.set(p.getFecha());
			//FechaRecordatorio.setValue(p.getFechaRecordatorio());
			
			

		} else {
			idLabel.setText("");
			
			nombreLabel.setText("");
		}
	}

	

	@FXML
	private void EditaEntrada() {
		int id = Integer.parseInt(idLabel.getText());
		if (id >= 0) {
			EntradaDAO j = new EntradaDAO(id);
			RegistroEntradaController.setEntrada(j);
			try {
				App.loadScene(new Stage(), "RegistroEntrada", "Actualizar");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Entrada f : AppController.Entradas) {
				if (f.getId() == j.getId()) {
					
					f.setId(j.getId());
					f.setDescripcion(j.getDescripcion());
					f.setId_a(j.getId_a());
				}
			}
			TablaEntrada.refresh();
			Initialize();
		}
	}

	@FXML
	private void AñadirEntrada() {
		Stage p = new Stage();

		EntradaDAO j = new EntradaDAO();
		RegistroEntradaController.setEntrada(j);
		System.out.println();
		try {
			App.loadScene(p, "RegistroEntrada", "Añadir Entrada ");

			TablaEntrada.refresh();
			Initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void mostrarAlertWarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Eliminar Entrada");
		alert.setContentText("Se cerrara la pestaña y borrara una Entrada de la base de datos");
		alert.showAndWait();
	}

	@FXML
	private void EliminarEntrada() {
		int id = Integer.parseInt(idLabel.getText());
		if (id >= 0) {
			EntradaDAO p = new EntradaDAO(id);
			Entrada j = new Entrada(p.getId(),  p.getDescripcion(), p.getFecha(), p.getFechaRecordatorio(), p.getId_a(), p.getEstado());
			AppController.Entradas.remove(j);
			p.eliminar();
			mostrarAlertWarning();
			App.closeScene((Stage) Eliminar.getScene().getWindow());

		}

	}
}
