import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	static int MAXIMO = 3;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//Definimos un numero de puerto
		int puerto = 5000;
		//Establecemos un contador para saber cuando el servidor debe parar de escuchar peticiones
		int numeroCliente = MAXIMO;
		//Y la direccion a la que va a responder, en este caso sera localhost
		InetAddress ia = InetAddress.getLocalHost();
		//Creamos un ServerSocket que responda al numero de puerto asignado y a la direccion localhost
		ServerSocket servidor = new ServerSocket(puerto,MAXIMO,ia);
		//Creo un bucle infinito para poder aceptar a los clientes y crear un hilo para responder a todos
		//de manera concurrente
		while(true) {
			if(numeroCliente > 0) {
				//Escuchamos cualquier peticion de conexion
				Socket cliente = servidor.accept();
				//Una vez establecida la conexion abro un hilo para ese cliente
				Hilo hiloCliente = new Hilo(cliente);
				//Una vez abierto el hilo para el cliente iniciamos el metodo run del hilo con el metodo start().
				hiloCliente.start();

				//Una vez se conecte un cliente reduciremos ese contador
				numeroCliente --;

				//Una vez se una el ultimo cliente, mostraremos un mensaje donde digamos que ya no se
				//aceptan mas peticiones
				if(numeroCliente == 0){
					System.out.println("El servidor esta lleno en estos momentos");
				}
			}
		}
	}

	
}
