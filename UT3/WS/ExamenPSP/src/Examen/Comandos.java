package Examen;

import java.io.Serializable;

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
