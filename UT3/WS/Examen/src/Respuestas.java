import java.io.Serializable;

//Lo importante a saber de la clase es que tiene que implementar el serializable para ser enviada
//desde el servidor al cliente y viceversa
public class Respuestas implements Serializable {
	String respuesta;

	public Respuestas(String respuesta) {
		super();
		this.respuesta = respuesta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

}
