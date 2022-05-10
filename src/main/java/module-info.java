module es.HNOS.HNOS {
	  requires javafx.controls;
	    requires javafx.fxml;
		requires javafx.graphics;
		requires java.sql;
		requires java.xml.bind;
		requires com.sun.xml.bind;
		requires mysql.connector.java;
		requires java.desktop;
		requires javafx.base;


    opens es.HNOS.HNOS to javafx.fxml;
    opens Connection to com.sun.xml.bind, java.xml.bind, javafx.baseEmpty;
    opens Utils to java.xml.bind, com.sun.xml.bind, javafx.baseEmpty;
    exports es.HNOS.HNOS;
}
