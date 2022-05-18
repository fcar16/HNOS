package Connection;

import java.io.File;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "conexion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Connection implements Serializable {

	private String host;

	private String db;

	private String usuario;

	private String contraseña;
	
	private String tipo;

	public Connection(String host, String db, String usuario, String contraseña,String tipo) {

		super();
		this.host = host;
		this.db = db;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.tipo=tipo;

	}

	public Connection() {
		this("", "", "", "","");
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "Connection [host=" + host + ", db=" + db + ", usuario=" + usuario + ", contraseña=" + contraseña + "]";
	}
/**
 * Metodo por el cual conseguimos la conexion a la base de datos
 */
	public void loadDataXML() {		
		File f = new File("conexion.xml");

		if (f.canRead()) {
			JAXBContext context;
			try {
			
				context = JAXBContext.newInstance(Connection.class);
				Unmarshaller um = context.createUnmarshaller();
				um.unmarshal(f);
				Connection miconextion = (Connection) um.unmarshal(f);
				System.out.println(miconextion.db);
				this.host = miconextion.host;
				this.db = miconextion.db;
				this.usuario = miconextion.usuario;
				this.tipo=miconextion.tipo;
				this.contraseña = miconextion.contraseña;
			} catch (JAXBException ex) {
				Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);

			}
		} else {
			System.out.println("Archivo no valido");
		}

	}
}
