package Ejemplo1POP3;

import java.io.IOException;

import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;

public class Ejemplo1POP3 {

	public static void main(String[] args) {
		String server = "localhost";
		String username = "pepe mel";
		String password = "jraiztlknptkpoxa";
		
		int puerto = 110;
		POP3SClient pop3 = new POP3SClient();
		try {
			pop3.connect(server,puerto);
			System.out.println("Conexion realizada");
			if(!pop3.login(username, password)) {
				System.out.println("Error al hacer el login");
			}else {
				POP3MessageInfo[] messages = pop3.listMessages();
				
				if(messages == null) {
					System.out.println("Imposible recuperar el mensaje");
				}else {
					System.out.println("Nº de mensajes: " + messages.length);
				}
				
				pop3.logout();
			}
			
			pop3.disconnect();
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
		}
		System.exit(0);
		
	}

}
