package battlegame.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Utilities {

	public static String loadFileAsString(String path) throws IOException {
		StringBuilder newString = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(path));
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
