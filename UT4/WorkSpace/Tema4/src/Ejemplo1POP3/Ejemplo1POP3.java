package Ejemplo1POP3;

import org.apache.commons.net.pop3.POP3MessageInfo;

public class Ejemplo1POP3 {

	public static void main(String[] args) {
		String server = "localhost";
		String username = "pepe mel";
		String password = "jraiztlknptkpoxa";
		
		int puerto = 110;
		POP3SClient pop3 = new POP3SClient();
		
		System.out.println("Conexcion realizada al servidor POP3 " + server);
	}

}
