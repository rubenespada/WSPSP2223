package ClaseURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejemplo2 {

	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("http://www.elaltozano.es");
		}catch(MalformedURLException e) {
			System.out.println(e);
		}
		
		BufferedReader in;
		try {
			InputStream inputStream = url.openStream();
			in = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
		}catch(IOException ex) {
			System.out.println(ex);
		}
	}

}
