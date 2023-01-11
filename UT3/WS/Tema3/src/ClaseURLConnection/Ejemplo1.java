package ClaseURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Ejemplo1 {

	public static void main(String[] args) {
		URL url = null;
		URLConnection urlCon = null;
		try {
			url = new URL("http://www.elaltozano.es");
			urlCon = url.openConnection();
			
			BufferedReader in;
			InputStream inputStream = urlCon.getInputStream();
			in = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while((line = in.readLine()) != null) {
				System.out.println(line);
			
		}
		}catch(MalformedURLException ex) {
			System.out.println(ex);
	}
		catch(IOException e) {
			System.out.println(e);
		}

}
}