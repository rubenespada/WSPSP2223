package Actividad5_3;

import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class Actividad5_3 {

	public static void main(String[] args) {

KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
kpg.initialize(2048);
KeyPair kp = kpg.generateKeyPair();
Key pvt = kp.getPrivate();

String outFile = "Clave";
FileOutputStream out = new FileOutputStream(outFile + ".privada");
out.write(pvt.getEncoded());
out.close();

Key pub = kp.getPublic();

String outFile = "Clave";
FileOutputStream out2 = new FileOutputStream(outFile + ".publica");
out2.write(pvt.getEncoded());
out2.close();

	}

}
