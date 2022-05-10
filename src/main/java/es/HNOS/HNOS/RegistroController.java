package es.HNOS.HNOS;



import java.io.IOException;
import ModelAsignatura.AsignaturaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class RegistroController {

	@FXML
	private TextField NombreAsignatura;

	@FXML
	private Button boton;

	public static AsignaturaDAO asignatura;

	@FXML
	private void CrearAsignatura()  {

		try {
			if (asignatura.getId() >= 0) {
				String nombre = NombreAsignatura.getText();
				asignatura.setNombre(nombre);
				asignatura.save();
			

			} else {
				AsignaturaDAO c = new AsignaturaDAO();
				String nombre = NombreAsignatura.getText();
				c.setNombre(nombre);
				c.save();
				
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		AppController.todas = AsignaturaDAO.GetAllAsignatura();
		App.closeScene((Stage) boton.getScene().getWindow());
		try {
			App.setRoot("Inicio");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static AsignaturaDAO getAsignatura() {
		return asignatura;
	}

	public static void setAsignatura(AsignaturaDAO asignatura) {
		RegistroController.asignatura = asignatura;
	}

}