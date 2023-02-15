import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Hilo extends Thread{
    //Este es el Socket del cliente
    Socket s;
    //Este es el flujo de salida para responder al cliente
    ObjectOutputStream fsal;
    //Este es el flujo de entrada para escuchar al cliente
    ObjectInputStream fent;

    //Este es el array de las frases que va a devolver al cliente
    static String frases [] = {"Hola que tal", "Adios, hasta luego", "Bienvenidos a todos"};
    //Este es el array de los libros que va a devolver al cliente
    static String libros[] = {"Libro 1", "Libro 2", "Libro 3"};

    //Este es el constructor donde a partir del Socket cliente que pasamos desde el bucle infinito
    //donde escuchamos las peticiones de conexion en el servidor, creamos el flujo de salida y de
    //entrada
    public Hilo(Socket socket) throws IOException {
        s = socket;
        fsal = new ObjectOutputStream(socket.getOutputStream());
        fent = new ObjectInputStream(socket.getInputStream());
    }


    public void run(){
        //Definimos un String simplemente para ver que es lo que nos llega del cliente
        String orden = "";
        try {
            do{
                //Vemos de que cliente proviene la peticion
                System.out.println("Escuchando al cliente" + s.getPort());
                //Recogemos el objeto comando que nos ha llegado del cliente
                Comandos comando = (Comandos) fent.readObject();
                orden = comando.getComando();
                //Mostramos el contenido del objeto simplemente para seguir el curso del programa
                System.out.println(orden);
                //Creamos una respuesta para el cliente
                Respuestas respuesta= new Respuestas(process(comando));
                //Mostramos la respuesta y a que cliente va dirigida para seguir el curso del programa
                System.out.println("Devolviendo :" + respuesta.getRespuesta() + " al cliente " + s.getPort());
                //La enviamos al cliente mediante el flujo de salida.
                fsal.writeObject(respuesta);
                fsal.reset();
            }while(!orden.equalsIgnoreCase("exit"));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Una vez terminamos de escuchar peticiones del cliente, cerramos tanto los flujos de entrada
        //como de salida y el Socket del cliente que estabamos escuchando en este hilo.
        try {
            System.out.println("Cerrando la conexion con el cliente " + s.getPort());
            s.close();
            fent.close();
            fsal.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    //Metodo para devolver la informacion
    public static String process(Comandos comando) {
        String orden = comando.getComando();
        String respuesta = "";
        int aleatorio;
        if(orden.equalsIgnoreCase("libro")) {
            aleatorio = ((int)(Math.random()* (libros.length-1)+0));
            respuesta = libros[aleatorio];
        }else if(orden.equalsIgnoreCase("frase")) {
            aleatorio = ((int)(Math.random()*(frases.length-1)+0));
            respuesta = frases[aleatorio];
        }else if(orden.equalsIgnoreCase("exit")) {
            respuesta = "Saliendo del programa....";
        }else {
            respuesta = "No es posible realizar la peticion; el comando no es correcto.";
        }
        return respuesta;
    }

}
