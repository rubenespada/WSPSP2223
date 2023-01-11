package ClaseURL;

import java.net.MalformedURLException;
import java.net.URL;

public class Ejemplo1 {

	public static void main(String[] args) {
		URL url;
		try {
			System.out.println("Constructor simple para una URL");
			url = new URL("http://docs.oracle.com/");
			Visualizar(url);
			
			System.out.println("Otro constructor simple.");
			url = new URL("http://localhost/PFC/gest/cli_gertion.php?S=3");
			Visualizar(url);
			
			System.out.println("Const. para protocolo + URL + Directorio");
			url = new URL("http", "docs.oracle.com", "/javase/10");
		}catch(MalformedURLException e) {
			System.out.println(e);
		}
	}
	
	public static void Visualizar(URL url) {
		System.out.println("");
		System.out.println("URL completa " + url.toString());
		System.out.println("getProtocol()" + url.getProtocol());
		System.out.println("getHost()" + url.getHost());
		System.out.println("getPort()" + url.getPort());
		System.out.println("getFile()" + url.getFile());
		System.out.println("getUserInfo()" + url.getUserInfo());
		System.out.println("getPath()" + url.getPath());
		System.out.println("getAuthority" + url.getAuthority());
		System.out.println("getQuery()" + url.getQuery());
		System.out.println("getDefaultPort()" + url.getDefaultPort());
	}

}
