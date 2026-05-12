// Main.java
// Wah Saw Tamalar
// CLI main

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner scanner = new Scanner(new File(args[0]));
		
		// Reader
		TokenReader reader = new TokenReader();
		
		// ArrayList
		ArrayList<Token> tokensList = reader.readTokens(scanner);
		scanner.close();
		
		// Recognizer
		Recognizer recognizer = new Recognizer(tokensList);
		
		// Call recognise
		boolean result = recognizer.recognise();
		System.out.println(result);
	}
}