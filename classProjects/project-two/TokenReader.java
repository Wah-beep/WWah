// TokenReader.java
// Wah Saw Tamalar
// Reads text files that contain token

import java.util.ArrayList;
import java.util.Scanner;

public class TokenReader {

    /**
     * Creates a list of tokens from the given input.
     * @param input A Scanner object pointed at text content.
     * @return An ArrayList of Token objects
     */
    public ArrayList<Token> readTokens( Scanner input) {
        //String aLine = input.nextLine();
        // FIXME: need to do real work here
		ArrayList<Token> tList = new ArrayList<Token>();
		while (input.hasNextLine()) {
			// Get the next line
			String line = input.nextLine().trim();
			if (line.isEmpty()) continue;
			
			String[] parts = line.split("\\s+");
			String lexeme = parts[0];	// Lexeme
			TokenType type = TokenType.valueOf(parts[1]);	// Token Type
			
			// Add to the ArrayList
			tList.add(new Token(lexeme, type));
		}
		System.out.println(tList);
        return tList;
    }
}