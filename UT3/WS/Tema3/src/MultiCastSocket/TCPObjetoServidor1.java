package MultiCastSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPObjetoServidor1 {
	public void main(String[]args) throws IOException, ClassNotFoundException {
		int numeroPuerto = 6000;
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		
		System.out.println("Esperando al cliente...");
		Socket cliente = servidor.accept();
		
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		
		Persona per = new Persona("Juan", 20);
		outObjeto.writeObject(per);
		System.out.println("Envio...");
		
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Persona dato = (Persona) inObjeto.readObject();
		
		System.out.println("Recibo: persona");
		
		outObjeto.close();
		inObjeto.close();
		cliente.close();
		servidor.close();
	}
}
