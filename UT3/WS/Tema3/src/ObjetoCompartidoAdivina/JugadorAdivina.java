package ObjetoCompartidoAdivina;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class JugadorAdivina {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		String host = "localhost";
		int puerto = 6001;
		Socket Cliente = new Socket(host, puerto);
		
		ObjectOutputStream fsalida = new ObjectOutputStream(Cliente.getOutputStream());
		ObjectInputStream fentrada = new ObjectInputStream(Cliente.getInputStream());
		
		Scanner sc = new Scanner(System.in);
		String cadena = "*";
		
		Datos datos = (Datos) fentrada.readObject();
		int identificador = datos.getIdentificador();
		System.out.println("Id jugador: " + identificador);
		System.out.println(datos.getCadena());
		
		if(!datos.isJuega()) {
			cadena = "*";
		}
		while(datos.isJuega() && !cadena.trim().equals("*")) {
			System.out.println("Intento: " + (datos.getIntentos() + 1) + " Introduce un numero:");
			cadena = sc.nextLine();
			Datos d = new Datos();
			
			if(!validarCadena(cadena)) continue;
			d.setCadena(cadena);
			
			fsalida.reset();
			fsalida.writeObject(d);
			
			datos = (Datos) fentrada.readObject();
			System.out.println("\t" + datos.getCadena());
			
			if(datos.getIntentos() >= 5) {
				System.out.println("Juego finalizado, no hay mas intentos");
				cadena = "*";
			}
			if(datos.isGana()) {
				System.out.println("Has gando el juego ha terminado");
				cadena = "*";
			}else {
				if(!datos.isJuega()) {
					System.out.println("El juego ha terminado, han adivinado el numero");
					cadena = "*";
				}
			}
		}
		fsalida.close();
		fentrada.close();
		System.out.println("Fin de proceso");
		sc.close();
		Cliente.close();
	}
	
	private static boolean validarCadena(String cadena) {
		boolean valor = false;
		try {
			Integer.parseInt(cadena);
			valor = true;
		}catch(NumberFormatException f) {
			
		}
		return valor;
	}

}
