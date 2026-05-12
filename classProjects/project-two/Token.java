// Token.java
// Auggie Eagle
// A Token class holding a lexeme and a token type

/**
 * Token represents a programming language token
 * for use by a compiler.
 */
public class Token {

    //////////////////////////////////
    //      Instance Variables      //
    //////////////////////////////////
    
    /** The lexeme of this Token */
    private String lexeme;
    
    /** The type of this Token */
    private TokenType type;
    
    //////////////////////////////////
    //         Constructors         //
    //////////////////////////////////
    
    /**
     * Creates a Token with the given lexeme and token type.
     * @param lex The lexeme of this token
     * @param type The token type for this token
     */
    public Token( String lex, TokenType type) {
        this.lexeme = lex;
        this.type = type;
    }
    
    //////////////////////////////////
    //     Getters and Setters      //
    //////////////////////////////////
    
    public String getLexeme() { return this.lexeme;}
    public TokenType getType() { return this.type;}
    
    //////////////////////////////////
    //      Overridden Methods      //
    //////////////////////////////////
    
    @Override
    public boolean equals( Object other) {
        if( this == other) { return true;}
        else if( !(other instanceof Token)) { return false;}
        else {
            Token otherToken = (Token)other;
            if( this.lexeme.equals(otherToken.lexeme) &&
                this.type == otherToken.type) { return true;}
            else { return false;}
        }
    }
    
    @Override
    public String toString() {
        return "[ " + this.type + " : " + this.lexeme + " ]";
    }
    
}