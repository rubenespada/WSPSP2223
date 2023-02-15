import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		//Definimos el mismo puerto que tenemos en el servidor
		int Puerto = 5000;
		//Definimos la direccion del servidor, en este caso es localhost
		InetAddress ia = InetAddress.getLocalHost();
		//Creamos el Socket para conectarnos al servidor
		Socket cliente = new Socket(ia, 5000);
		String orden = "";

		//Vemos que el Socket se ha creado correctamente y el numero de puerto al que se le ha asignado
		//una vez establecida la conexion
		System.out.println("Inicio del cliente");
		System.out.println(cliente.getLocalPort());
		//Creamos un objeto respuesta sobre el que recibiremos la informacion del servidor
		Respuestas entrada;
		//Creamos los flujos de entrada y salida para comunicarnos con el servidor
		ObjectOutputStream sal = new ObjectOutputStream(cliente.getOutputStream());
		ObjectInputStream ent = new ObjectInputStream(cliente.getInputStream());
		do {
			//Escribimos el comando y lo guardamos en un objeto Comando
			System.out.println("Escriba el siguiente comando");
			orden = sc.next();
			Comandos comando = new Comandos(orden);
			//Mandamos el objeto al servidor mediante el flujo de salida.
			sal.writeObject(comando);
			//Recibimos la respuesta mediante el flujo de entrada y la mostramos.
			entrada = (Respuestas) ent.readObject();
			System.out.println("Servidor> " + entrada.getRespuesta());
		} while (!orden.equalsIgnoreCase("exit"));
		//Una vez escrito exit, cerramos tanto el Socket como los flujos de entrada y salida.
		System.out.println("Cerrado");
		cliente.close();
		sal.close();
		ent.close();
	}
}
