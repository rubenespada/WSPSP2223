import java.io.Serializable;

//Lo importante a saber de la clase es que tiene que implementar el serializable para ser enviada
//desde el servidor al cliente y viceversa
public class Comandos implements Serializable{
	String comando;
	
	public Comandos(String comando) {
		super();
		this.comando = comando;
	}
	
	public Comandos() {
		super();
	}

	public String getComando() {
		return comando;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}
	
}
