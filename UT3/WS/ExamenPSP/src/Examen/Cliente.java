package Examen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		int Puerto = 5000;
		InetAddress ia = InetAddress.getLocalHost();
		Socket cliente = new Socket(ia, 5000);
		String orden = "";
		System.out.println("Inicio del cliente");
		System.out.println(cliente.getLocalPort());
		Comandos entrada;
		ObjectOutputStream sal = new ObjectOutputStream(cliente.getOutputStream());
		ObjectInputStream ent = new ObjectInputStream(cliente.getInputStream());
		do {
			System.out.println("Escriba el siguiente comando");
			orden = sc.next();
			Comandos comando = new Comandos(orden);
			sal.writeObject(comando);
			entrada = (Comandos) ent.readObject();
			System.out.println(entrada.getComando());
		} while (!orden.equalsIgnoreCase("exit"));
		System.out.println("Cerrado");
		cliente.close();
		sal.close();
		ent.close();
	}
}
