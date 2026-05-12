// TokenTest.java

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests of the Token class, to be run with JUnit 5.
 *
 * To compile against the JUnit classes on the command line, use
 * a command like
 * <pre>
 *   javac -cp ./junit-platform-console-standalone-6.0.2.jar:. Token.java TokenTest.java
 * </pre>
 * On Windows it may be necessary to quote the classpath:
 * <pre>
 *   javac -cp "./junit-platform-console-standalone-6.0.2.jar;." Token.java TokenTest.java
 * <pre>
 * In order to run the tests in this class on the command line, use
 * a command like
 * <pre>
 *   java -jar ./junit-platform-console-standalone-6.0.2.jar execute -cp . --scan-classpath .
 * </pre>
 * or
 * <pre>
 *   java -jar ./junit-platform-console-standalone-6.0.2.jar execute -cp . --select-class TokenTest
 * </pre>
 * 
 * @author Erik Steinmetz
 */
public class TokenTest {

    public TokenTest() {
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
    public void testGetLexeme() {
        System.out.println("Running getLexeme test");
        Token instance = new Token( "3.7", TokenType.NUMBER);
        
        String expected = "3.7";
        String actual = instance.getLexeme();
        assertEquals(expected, actual);    
    }
    
}
