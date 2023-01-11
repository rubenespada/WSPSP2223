package ClaseInetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EJ3_1 {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		InetAddress dir = null;
		String nombre;
		System.out.println("Introduzca el nombre de la maquina.");
		nombre = sc.next();
		dir = InetAddress.getByName(nombre);
		mostrarInformacion(dir);
	}
	
	public static void mostrarInformacion(InetAddress dir) {
		System.out.println("Nombre " + dir);
		System.out.println("Direccion: " + dir.getHostAddress());
	}

}
