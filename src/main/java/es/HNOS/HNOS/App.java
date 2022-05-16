package es.HNOS.HNOS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;





/**
 * JavaFX App
 */
public class App extends Application {

	 private static Scene scene;
/**
 * Metodo Con el que inicia el programa
 */
	    @Override
	    public void start(Stage stage) throws IOException {
	    	stage.setTitle("HNOS");
	        scene = new Scene(loadFXML("Inicio"), 640, 480);
	        stage.setScene(scene);
	        stage.show();
	    }

	    static void setRoot(String fxml) throws IOException {
	        scene.setRoot(loadFXML(fxml));
	    }

	    private static Parent loadFXML(String fxml) throws IOException {
	        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
	        return fxmlLoader.load();
	    }
	    public static void loadScene(Stage stage, String fxml, String title) throws IOException{
	        Parent p=loadFXML(fxml);
	        stage.setScene(new Scene(p));
	        //stage.getIcons().add(new Image(App.class.getResourceAsStream("")));
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setTitle(title);
	        stage.showAndWait();
	    }
	    

	    public static void main(String[] args) {
	        launch();
	    }
	    
	    
	    public static void closeScene(Stage stage) {
	        stage.close();
	    }
	    
	    
    


}