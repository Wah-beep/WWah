// RecognizerTest
// Wah Saw Tamalar

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests of the Recognizer class, to be run with JUnit 6.
 *
 * To compile against the JUnit classes on the command line, use
 * a command like
 * <pre>
 *   javac -cp ./junit-platform-console-standalone-6.0.2.jar:. Token.java Recognizer.java RecognizerTest.java
 * </pre>
 * On Windows it may be necessary to quote the classpath:
 * <pre>
 *   javac -cp "./junit-platform-console-standalone-6.0.2.jar;." Token.java Recognizer.java RecognizerTest.java
 * <pre>
 * In order to run the tests in this class on the command line, use
 * a command like
 * <pre>
 *   java -jar ./junit-platform-console-standalone-6.0.2.jar execute -cp . --scan-classpath .
 * </pre>
 * or
 * <pre>
 *   java -jar ./junit-platform-console-standalone-6.0.2.jar execute -cp . --select-class RecognizerTest
 * </pre>
 * 
 * @author Wah Saw Tamalar
*/

public class RecognizerTest{
	
	public RecognizerTest() {
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
     * Test of Recognizer
     */
    @Test
    public void testRecognizerAddop() {
        System.out.println("Running RecognizerAddop() test");

        ArrayList<Token> tokensList = new ArrayList<>();
        tokensList.add( new Token( "4", TokenType.NUMBER));
        tokensList.add( new Token( "+", TokenType.ADD));
		tokensList.add( new Token( "3", TokenType.NUMBER));

		Recognizer instance = new Recognizer(tokensList);
		boolean result = instance.recognise();
		assertTrue(result);
		
		ArrayList<Token> tokensList2 = new ArrayList<>();
		tokensList2.add( new Token( "4", TokenType.NUMBER));
		tokensList2.add( new Token( "-", TokenType.SUBTRACT));
		tokensList2.add( new Token( "3", TokenType.NUMBER));
		
		instance = new Recognizer(tokensList2);
		result = instance.recognise();
		assertTrue(result);
    }
	
	/**
     * Test of Recognizer
     */
    @Test
    public void testRecognizerAddopError() {
        System.out.println("Running RecognizerAddopError() test");

        ArrayList<Token> tokensList = new ArrayList<>();
        tokensList.add( new Token( "4", TokenType.NUMBER));
        tokensList.add( new Token( "+", TokenType.ADD));
		tokensList.add( new Token( "*", TokenType.MULTIPLY));
		
		Recognizer instance = new Recognizer(tokensList);
		boolean result = instance.recognise();
		assertFalse(result);
    }
	
	/**
     * Test of Recognizer
     */
    @Test
    public void testRecognizerMulop() {
        System.out.println("Running RecognizerMulop() test");

        ArrayList<Token> tokensList = new ArrayList<>();
        tokensList.add( new Token( "4", TokenType.NUMBER));
        tokensList.add( new Token( "*", TokenType.MULTIPLY));
		tokensList.add( new Token( "3", TokenType.NUMBER));
		
		Recognizer instance = new Recognizer(tokensList);
		boolean result = instance.recognise();
		assertTrue(result);
		
		ArrayList<Token> tokensList2 = new ArrayList<>();
        tokensList2.add( new Token( "4", TokenType.NUMBER));
        tokensList2.add( new Token( "/", TokenType.DIVIDE));
		tokensList2.add( new Token( "3", TokenType.NUMBER));
		
		instance = new Recognizer(tokensList2);
		result = instance.recognise();
		assertTrue(result);
    }
	
	/**
     * Test of Recognizer
     */
    @Test
    public void testRecognizerMulopError() {
        System.out.println("Running RecognizerMulopError() test");

        ArrayList<Token> tokensList = new ArrayList<>();
        tokensList.add( new Token( "4", TokenType.NUMBER));
        tokensList.add( new Token( "*", TokenType.MULTIPLY));
		tokensList.add( new Token( "-", TokenType.SUBTRACT));
		
		Recognizer instance = new Recognizer(tokensList);
		boolean result = instance.recognise();
		assertFalse(result);
    }
	
	/**
     * Test of Recognizer
     */
    @Test
    public void testRecognizerFooBar() {
        System.out.println("Running RecognizerFooBar() test");

        ArrayList<Token> tokensList = new ArrayList<>();
        tokensList.add( new Token( "foo", TokenType.ID));
        tokensList.add( new Token( "+", TokenType.ADD));
		tokensList.add( new Token( "bar", TokenType.ID));
		tokensList.add( new Token( "(", TokenType.LEFT_PARENTHESIS));
		tokensList.add( new Token( "foo1", TokenType.ID));
		tokensList.add( new Token( ",", TokenType.COMMA));
		tokensList.add( new Token( "foo2", TokenType.ID));
		tokensList.add( new Token( ")", TokenType.RIGHT_PARENTHESIS));
		
		Recognizer instance = new Recognizer(tokensList);
		boolean result = instance.recognise();
		assertTrue(result);
		
		ArrayList<Token> tokensList2 = new ArrayList<>();
		tokensList2.add( new Token( "foo", TokenType.ID));
		tokensList2.add( new Token( "(", TokenType.LEFT_PARENTHESIS));
		tokensList2.add( new Token( ")", TokenType.RIGHT_PARENTHESIS));
		
		instance = new Recognizer(tokensList2);
		result = instance.recognise();
		assertTrue(result);
    }
	
	/**
     * Test of Recognizer
     */
    @Test
    public void testRecognizerFooBarError() {
        System.out.println("Running RecognizerFooBarError() test");

        ArrayList<Token> tokensList = new ArrayList<>();
        tokensList.add( new Token( "foo", TokenType.ID));
        tokensList.add( new Token( "(", TokenType.LEFT_PARENTHESIS));
		tokensList.add( new Token( "x", TokenType.ID));
		tokensList.add( new Token( ",", TokenType.COMMA));
		tokensList.add( new Token( ")", TokenType.RIGHT_PARENTHESIS));
		
		Recognizer instance = new Recognizer(tokensList);
		boolean result = instance.recognise();
		assertFalse(result);
    }
	
	/**
     * Test of Recognizer
     */
    @Test
    public void testRecognizerFooBarCommaError() {
        System.out.println("Running RecognizerFooBarCommaError() test");

        ArrayList<Token> tokensList = new ArrayList<>();
        tokensList.add( new Token( "foo", TokenType.ID));
        tokensList.add( new Token( "+", TokenType.ADD));
		tokensList.add( new Token( "bar", TokenType.ID));
		tokensList.add( new Token( "(", TokenType.LEFT_PARENTHESIS));
		tokensList.add( new Token( "foo1", TokenType.ID));
		tokensList.add( new Token( "foo2", TokenType.ID));
		tokensList.add( new Token( ")", TokenType.RIGHT_PARENTHESIS));
		
		Recognizer instance = new Recognizer(tokensList);
		boolean result = instance.recognise();
		assertFalse(result);
    }
	
	/**
     * Test of Recognizer
     */
    @Test
    public void testRecognizerPara() {
        System.out.println("Running RecognizerPara() test");

        ArrayList<Token> tokensList = new ArrayList<>();
        tokensList.add( new Token( "(", TokenType.LEFT_PARENTHESIS));
        tokensList.add( new Token( "4", TokenType.NUMBER));
		tokensList.add( new Token( "+", TokenType.ADD));
		tokensList.add( new Token( "3", TokenType.NUMBER));
		tokensList.add( new Token( ")", TokenType.RIGHT_PARENTHESIS));
		tokensList.add( new Token( "*", TokenType.MULTIPLY));
		tokensList.add( new Token( "2", TokenType.NUMBER));
		
		Recognizer instance = new Recognizer(tokensList);
		boolean result = instance.recognise();
		assertTrue(result);
    }
	
	/**
     * Test of Recognizer
     */
    @Test
    public void testRecognizerParaError() {
        System.out.println("Running RecognizerParaError() test");

        ArrayList<Token> tokensList = new ArrayList<>();
        tokensList.add( new Token( "(", TokenType.LEFT_PARENTHESIS));
        tokensList.add( new Token( "4", TokenType.NUMBER));
		tokensList.add( new Token( "+", TokenType.ADD));
		tokensList.add( new Token( "3", TokenType.NUMBER));
		tokensList.add( new Token( "*", TokenType.MULTIPLY));
		tokensList.add( new Token( "2", TokenType.NUMBER));
		
		Recognizer instance = new Recognizer(tokensList);
		boolean result = instance.recognise();
		assertFalse(result);
    }
}