package ObjetoCompartidoAdivina;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloServidorAdivina extends Thread{
	ObjectInputStream fentrada;
	ObjectOutputStream fsalida;
	Socket socket = null;
	ObjetoCompartido objeto;
	int identificador;
	int intentos = 0;

	public HiloServidorAdivina(Socket socket, ObjetoCompartido objeto, int identificador, int intentos) {
		this.socket = socket;
		this.objeto = objeto;
		this.identificador = identificador;
		try {
			fsalida = new ObjectOutputStream(socket.getOutputStream());
			fentrada = new ObjectInputStream(socket.getInputStream());
		} catch (IOException ex) {
			System.out.println("Error");
		}
	}
	
	public void run() {
		System.out.println("Cliente conectado:" + identificador);
		Datos datos = new Datos("Adivina un numero entre 1 y 25", intentos,identificador);
		if(objeto.seAcabo()) {
			datos.setCadena("Lo sentimos, el juego ha terminador");
			datos.setJuega(false);
		}
		try {
			fsalida.reset();
			fsalida.writeObject(datos);
		}catch(IOException ex) {
			System.out.println("Error");
			return;
		}
		while(!objeto.seAcabo() || !(datos.getIntentos() == 5)) {
			int numecli = 0;
			try {
				Datos d = (Datos) fentrada.readObject();
				numecli = Integer.parseInt(d.getCadena());
			}catch(IOException ex) {
				System.out.println("Error");
				break;
			}catch(NumberFormatException n) {
				System.out.println("El jugador" + identificador + " se ha desconectado.");
				break;
			}catch(ClassNotFoundException c) {
				c.printStackTrace();
				break;
			}
			
			String cad = objeto.nuevaJugada(identificador, numecli);
			intentos ++;
			
			datos = new Datos(cad,intentos,identificador);
			if(objeto.seAcabo()) {
				datos.setJuega(false);
				if(identificador == objeto.getGanador()) {
					datos.setGana(true);
				}
			}
			
			try {
				fsalida.reset();
				fsalida.writeObject(datos);
			}catch(IOException ex) {
				System.out.println("Error");
				ex.getMessage();
			}catch(NullPointerException n) {
				System.out.println("El jugador " + identificador + " se ha desconectado");
				break;
			}
			
			if(objeto.seAcabo()) {
				System.out.println("El juego ha acabado");
				System.out.println("Desconecta " + identificador);
			}
			
			try {
				fsalida.close();
				fentrada.close();
				socket.close();
			}catch(IOException ex) {
				System.out.println("Error al cerrar");
				ex.printStackTrace();
			}
		}
	}

}
