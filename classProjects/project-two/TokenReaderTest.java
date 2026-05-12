// TokenTest.java

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests of the TokenReader class, to be run with JUnit 6.
 *
 * To compile against the JUnit classes on the command line, use
 * a command like
 * <pre>
 *   javac -cp ./junit-platform-console-standalone-6.0.2.jar:. Token.java TokenReader.java TokenReaderTest.java
 * </pre>
 * On Windows it may be necessary to quote the classpath:
 * <pre>
 *   javac -cp "./junit-platform-console-standalone-6.0.2.jar;." Token.java TokenReader.java TokenReaderTest.java
 * <pre>
 * In order to run the tests in this class on the command line, use
 * a command like
 * <pre>
 *   java -jar ./junit-platform-console-standalone-6.0.2.jar execute -cp . --scan-classpath .
 * </pre>
 * or
 * <pre>
 *   java -jar ./junit-platform-console-standalone-6.0.2.jar execute -cp . --select-class TokenReaderTest
 * </pre>
 * 
 * @author Erik Steinmetz
 */
public class TokenReaderTest {

    public TokenReaderTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Running BeforeAll");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("Running AfterAll");
    }
    
    @BeforeEach
    public void setUp() {
        System.out.println("Running BeforeEach");
    }
    
    @AfterEach
    public void tearDown() {
        System.out.println("Running AfterEach");
    }
    
    /**
     * Test of getLexeme method, of class Token.
     */
    @Test
    public void testReadTokens() {
        System.out.println("Running readTokens() test");
        TokenReader instance = new TokenReader( );
        Scanner s = new Scanner( "37.5 NUMBER\n* MULTIPLY");
        
        ArrayList<Token> expected = new ArrayList<>();
        expected.add( new Token( "37.5", TokenType.NUMBER));
        expected.add( new Token( "*", TokenType.MULTIPLY));
        ArrayList<Token> actual = instance.readTokens(s);
        assertEquals(expected, actual);
    }
	
	/**
     * Test of getLexeme method, of class Token. Second Test
     */
    @Test
    public void testReadTokens2() {
        System.out.println("Running readTokens2() test");
        TokenReader instance = new TokenReader( );
		
		// Second Test
		System.out.println("Running readTokens() test two");
		Scanner s = new Scanner("( LEFT_PARENTHESIS\nn ID\n) RIGHT_PARENTHESIS\n/ DIVIDE\n2 NUMBER");
		ArrayList<Token> expected2 = new ArrayList<>();
		expected2.add(new Token("(", TokenType.LEFT_PARENTHESIS));
		expected2.add(new Token("n", TokenType.ID));
		expected2.add(new Token(")", TokenType.RIGHT_PARENTHESIS));
		expected2.add(new Token("/", TokenType.DIVIDE));
		expected2.add(new Token("2", TokenType.NUMBER));
		ArrayList<Token> actual2 = instance.readTokens(s);
		assertEquals(expected2, actual2);
    }
    
}
