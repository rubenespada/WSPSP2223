package MultiCastSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;mo

public class Ejemplo1 {
	public static void main(String[]args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		MulticastSocket ms = new MulticastSocket();
		int Puerto = 12345;
		InetAddress grupo = InetAddress.getByName("225.0.0.1");
		String cadena = "";
		while(!cadena.trim().equals("*")) {
			System.out.println("Datos a enviar al grupo:");
			cadena = in.readLine();
			DatagramPacket paquete = new DatagramPacket(cadena.getBytes(),cadena.length(),grupo, Puerto);
			ms.send(paquete);
		}
		ms.close();
	}
}
