package Ejemplo7;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

public class Ejemplo7 {

	public static void main(String[] args) {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA");
			SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
			keygen.initialize(2048, numero);

			KeyPair par = keygen.generateKeyPair();
			PrivateKey clavepriv = par.getPrivate();
			PublicKey clavepubli = par.getPublic();

			Signature dsa = Signature.getInstance("SHA256withDSA");
			dsa.initSign(clavepriv);
			String mensaje = "Este mensaje va a ser firmado";
			dsa.update(mensaje.getBytes());

			byte[] firma = dsa.sign();

			Signature verificadsa = Signature.getInstance("SHA256withDSA");
			verificadsa.initVerify(clavepubli);
			verificadsa.update(mensaje.getBytes());
			boolean check = verificadsa.verify(firma);

			if (check) {
				System.out.println("FIRMA VERIFICADA CON CLAVE PUBLICA");
			} else {
				System.out.println("FIRMA NO VERIFICADA");
			}
		} catch (NoSuchAlgorithmException e1) {
			System.out.println(e1.getMessage());
		} catch (InvalidKeyException e2) {
			System.out.println(e2.getMessage());
		} catch (SignatureException e3) {
			System.out.println(e3.getMessage());
		}
	}

}
