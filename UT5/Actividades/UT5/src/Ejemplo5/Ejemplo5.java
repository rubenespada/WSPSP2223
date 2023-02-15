package Ejemplo5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Ejemplo5 {

	public static void main(String[] args) {
		try {
			FileOutputStream fileout = new FileOutputStream("DATOS.DAT");
			ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String datos = "En un lugar de la mancha";
			byte dataBytes[] = datos.getBytes();
			
			md.update(dataBytes);
			byte resumen[] = md.digest();
			dataOS.writeObject(datos);
			dataOS.writeObject(resumen);
			dataOS.close();
			fileout.close();
			
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}catch(NoSuchAlgorithmException e2) {
			System.out.println(e2.getMessage());
		}
	}

}
