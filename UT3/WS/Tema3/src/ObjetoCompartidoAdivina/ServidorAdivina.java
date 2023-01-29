package ObjetoCompartidoAdivina;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAdivina {
	public void main(String[] args) throws IOException {
		ServerSocket servidor = new ServerSocket(6001);
		System.out.println("Servidor iniciado");
		
		int numero = (int) (1+ 25*Math.random());
		System.out.println("Numero a adivinar " + numero);
		
		int id = 0;
		while(true) {
			ObjetoCompartido objeto = new ObjetoCompartido(5);
			Socket cliente = new Socket();
			cliente = servidor.accept();
			id++;
			HiloServidorAdivina hilo = new HiloServidorAdivina(cliente,objeto,25,id);
			hilo.start();
		}
	}
}
