package battlegame.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utilities {

	public static String loadFileAsString(String path) throws IOException {
		StringBuilder newString = new StringBuilder();
		InputStream inputStream = Utilities.class.getResourceAsStream(path);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String line = reader.readLine();
		while(line != null) {
			newString.append(line + "\n");
			line = reader.readLine();
		}
		return newString.toString();
		
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
