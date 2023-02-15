package Examen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	static int MAXIMO = 3;
	static String frases [] = {"Hola que tal", "Adios, hasta luego", "Bienvenidos a todos"};
	static String libros[] = {"Libro 1", "Libro 2", "Libro 3"};
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int puerto = 5000;
		InetAddress ia = InetAddress.getLocalHost();
		ServerSocket servidor = new ServerSocket(puerto,MAXIMO,ia);
		Socket cliente = servidor.accept();
		System.out.println("Escuchando al cliente" + cliente.getPort());
		ObjectInputStream ent = new ObjectInputStream(cliente.getInputStream());
		Comandos comando = (Comandos) ent.readObject();
		ObjectOutputStream sal = new ObjectOutputStream(cliente.getOutputStream());
		sal.writeObject(new Respuestas(process(comando)));
		System.out.println("Terminado");
	}
	
	public static String process(Comandos comando) {
		String orden = comando.getComando();
		String respuesta = "";
		int aleatorio;
		if(orden.equalsIgnoreCase("libro")) {
			aleatorio = ((int)(Math.random()*2+0));
			respuesta = libros[aleatorio];
		}else if(orden.equalsIgnoreCase("frase")) {
			aleatorio = ((int)(Math.random()*2+0));
			respuesta = frases[aleatorio];
		}else if(orden.equalsIgnoreCase("exit")) {
			System.out.println("Saliendo del programa....");
		}else {
			System.out.println("No es posible realizar la peticion; el comando no es correcto.");
		}
		return respuesta;
	}
	
}
