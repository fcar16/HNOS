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
	/**
	 * Label que tiene la fecha de recordatorio
	 */
	@FXML
	private Label FechaRecordatorio;
	
	/**
	 * Label que tiene la fecha en la que se a guardado la entrada
	 */
	@FXML
	private Label Fecha;
/**
 * Label que nos muestra la id de la entrada selecionada
 */
	@FXML
	private Label idLabel;
/**
 * Label que nos muestra el nombre/descripcion de la entrada selecionada
 */
	@FXML
	private Label nombreLabel;
/**
 * Boton de eliminar 
 */
	@FXML
	private Button Eliminar;
	/**
	 * Tabla De lo que contiene la entrada
	 */
	@FXML
	TableView<Entrada> TablaEntrada;
/*
 * Columna de la tabla que muestra la id de la entrada
 */
	@FXML
	private TableColumn<Entrada, String> idColumna;
	/*
	 * Columna de la tabla que muestra el nombre de la entrada
	 */
	@FXML
	private TableColumn<Entrada, String> nombreColumna;
/**
 * Metodo En el que inicia todo en este en concreto inicia la tabla y configura
 */
	@FXML
	protected void initialize() {
		System.out.println("Cargando...");

		muestraInfo(null);
		configuraTabla();
		
		TablaEntrada.setItems(FXCollections.observableArrayList(AppController.Entradas));
		TablaEntrada.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			muestraInfo(newValue);
		});
	}
	
	
/**
 * Metodo echo para configurar el tableview
 */
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
/**
 * Metodo Para mostar información en las vistas
 * @param p Entrada que le pasas para que muestre la informacion de ella
 */
	private void muestraInfo(Entrada p) {

		if (p != null) {
			idLabel.setText(Integer.toString(p.getId()));
				
			nombreLabel.setText(p.getDescripcion());
			Fecha.setText(p.getFecha());
			FechaRecordatorio.setText(p.getFechaRecordatorio());
			
			

		} else {
			idLabel.setText("");
			Fecha.setText("");
			FechaRecordatorio.setText("");
			nombreLabel.setText("");
		}
	}

	
/**
 * Metodo el cual nos sirve para editar la entrada selecionada asignado a un boton
 */
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
					f.setFecha(j.getFecha());
					f.setFechaRecordatorio(j.getFechaRecordatorio());
				}
				
			}
			TablaEntrada.refresh();
			initialize();
		}
	}
/**
 * Metodo para añadir una entrada asignado a un boton
 */
	@FXML
	private void AñadirEntrada() {
		Stage p = new Stage();

		EntradaDAO j = new EntradaDAO();
		RegistroEntradaController.setEntrada(j);
		System.out.println();
		try {
			App.loadScene(p, "RegistroEntrada", "Añadir Entrada ");

			TablaEntrada.refresh();
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * Metodo en cual muestra una ventana emergente con un mensaje escrito
 */
	private void mostrarAlertWarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Eliminar Entrada");
		alert.setContentText("Se cerrara la pestaña y borrara una Entrada de la base de datos");
		alert.showAndWait();
	}
/**
 * Metodo que elimina una entrada de la base de datos asignada a un boton
 */
	@FXML
	private void EliminarEntrada() {
		int id = Integer.parseInt(idLabel.getText());
		if (id >= 0) {
			EntradaDAO p = new EntradaDAO(id);
			Entrada j = new Entrada(p.getId(),  p.getDescripcion(), p.getFecha(), p.getFechaRecordatorio(), p.getId_a());
			AppController.Entradas.remove(j);
			p.eliminar();
			mostrarAlertWarning();
			App.closeScene((Stage) Eliminar.getScene().getWindow());

		}

	}
}
