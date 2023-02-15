package correo;

import java.security.*;
import java.security.spec.*;
import org.apache.commons.net.smtp.*;
import javax.net.ssl.*;
import java.io.*;

public class Enviar_Correo {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, InvalidKeyException, InvalidKeySpecException {
		
		AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();
		
		String server = "smtp.gmail.com";
		
		String username = "pepemelbetico1492@gmail.com";
		
		String password = "jraiztlknptkpoxa";
		
		int puerto = 587;
		
		String remitente = "pepemelbetico1492@gmail.com";
		
		try {
			
			int respuesta;
			
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			
			kmf.init(null, null);
			
			KeyManager km = kmf.getKeyManagers() [0];
			
			client.connect(server, puerto);
			
			System.out.println("1 - " + client.getReplyString());
			
			client.setKeyManager(km);
			
			respuesta = client.getReplyCode();
			
			if (!SMTPReply.isPositiveCompletion(respuesta)) {
				
				client.disconnect();
				
				System.err.println("CONEXIÓN RECHAZADA");
				
				System.exit(1);
				
			}
			
			client.ehlo(server);
			
			System.out.println("2 - " + client.getReplyString());
			
			if (client.execTLS()) {
				
				System.out.println("3 - " + client.getReplyString());

				if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {
					
					System.out.println("4 - " + client.getReplyString());
					
					String destino1 = "pepemelbetico1492@gmail.com";
					
					String asunto = "Prueba de SMTPClient con GMAIL";
					
					String mensaje = "Hola. \nEnviando saludos. \nUsando GMAIL. \nChao.";
					
					SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destino1, asunto);
					
					client.setSender(remitente);
					
					client.addRecipient(destino1);
										
					System.out.println("5 - " + client.getReplyString());
					
					Writer writer = client.sendMessageData();
					
					if (writer == null) {
						
						System.out.println("FALLO AL ENVIAR DATA");
						
						System.exit(1);
						
					}
					
					writer.write(cabecera.toString());
					
					writer.write(mensaje);
					
					writer.close();
					
					System.out.println("6 - " + client.getReplyString());
					
					boolean exito = client.completePendingCommand();
					
					System.out.println("7 - " + client.getReplyString());
					
					if (!exito) {
						
						System.out.println("FALLO AL FINALIZAR TRANSACCIÓN");
						
						System.exit(1);
						
					} else {
						
						System.out.println("MENSAJE ENVIADO CON EXITO");
						
					}
					
				} else {
					
					System.out.println("USUARIO NO AUTENTICADO");
					
				}
				
			} else {
				
				System.out.println("FALLO AL EJECUTAR STARTTLS");
				
			}
			
		} catch (IOException e) {
			
			System.err.println("Could not connect to server.");
			
			e.printStackTrace();
			
			System.exit(1);
			
		}
		
		try {
			
			client.disconnect();
			
		} catch (IOException f) {
			
			f.printStackTrace();
			
		}
		
		System.out.println("Fin de envío");
		
		System.exit(0);
		
	}

}