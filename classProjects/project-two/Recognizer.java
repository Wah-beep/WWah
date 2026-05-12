// Recognizer
// Wah Saw Tamalar
// Recognizer, stack-based parser, determine whether the list of Token opbjects represents a correct equation

import java.util.ArrayList;

public class Recognizer {
	
	private ArrayList<Token> list;
	private Token lookahead;
	
	// Constructor
	public Recognizer(ArrayList<Token> aList) {
		this.list = aList;
	}
	
	/**
	* Recursive-descent parser
	* @return boolean
	*/
	public boolean recognise() {
		lookahead = list.get(0);
		try {
			exp();
		} catch(Exception e) {
			return false;
		}
		
		if (list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	// exp()
	public void exp() {
		term();
		simple_part();
	}
	
	// term()
	public void term() {
		factor();
		simple_term();
	}
	
	// simple_part for addop
	public void simple_part() {
		// If lookahead is not null, and ADD OR SUBTRACT
		if (lookahead != null && (lookahead.getType() == TokenType.ADD || lookahead.getType() == TokenType.SUBTRACT)) {
			// Call addop()
			addop();
			term();
			simple_part();
		}
	}
	
	// simple_part2 for mulop
	public void simple_term() {
		// If lookahead is not null, and MULTIPLY OR DIVIDE
		if (lookahead != null && (lookahead.getType() == TokenType.MULTIPLY || lookahead.getType() == TokenType.DIVIDE)) {
			// Call mulop()
			mulop();
			factor();
			simple_term();
		}
	}
	
	/**
	* Remove ADD or SUBTRACT, and set lookahead
	* @throws RuntimeException
	*/
	public void addop() {
		if (lookahead.getType() == TokenType.ADD) {
			list.remove(0);
			
			// Check if the list is empty and set lookahead
			if (!list.isEmpty()) {
				lookahead = list.get(0);
			} else {
				lookahead = null;
			}
		} else if (lookahead.getType() == TokenType.SUBTRACT) {
			list.remove(0);
			
			// Check if the list is empty and set lookahead
			if (!list.isEmpty()) {
				lookahead = list.get(0);
			} else {
				lookahead = null;
			}
		} else {
			throw new RuntimeException("addop FAIL"); // FAIL
		}
	}
	
	/**
	* Remove left and right parenthesis, and set lookahead
	* @throws RuntimeException
	*/
	public void factor() {
		if (lookahead.getType() == TokenType.ID) {
			namedval();
		} else if (lookahead.getType() == TokenType.NUMBER) {
			list.remove(0);
			
			// Check if the list is empty and set lookahead
			if (!list.isEmpty()) {
				lookahead = list.get(0);
			} else {
				lookahead = null;
			}
		} else if (lookahead.getType() == TokenType.LEFT_PARENTHESIS) {
			list.remove(0);
			
			// Check if the list is empty and set lookahead
			if (!list.isEmpty()) {
				lookahead = list.get(0);
			} else {
				lookahead = null;
			}
			exp();
			
			// Check if the list is not empty and right parenthesis
			if (lookahead != null && lookahead.getType() == TokenType.RIGHT_PARENTHESIS) {
				list.remove(0);
				
				// Check if the list is empty and set lookahead
				if (!list.isEmpty()) {
					lookahead = list.get(0);
				} else {
					lookahead = null;
				}
			} else {
				throw new RuntimeException("Right Parenthesis FAIL"); // FAIL
			}
		} else {
			throw new RuntimeException("factor FAIL"); // FAIL
		}
	}
	
	/**
	* Remove MULTIPLY or DIVIDE, and set lookahead
	* @throws RuntimeException
	*/
	public void mulop() {
		if (lookahead.getType() == TokenType.MULTIPLY) {
			list.remove(0);
			
			// Check if the list is empty and set lookahead
			if (!list.isEmpty()) {
				lookahead = list.get(0);
			} else {
				lookahead = null;
			}
		} else if (lookahead.getType() == TokenType.DIVIDE) {
			list.remove(0);
			
			// Check if the list is empty and set lookahead
			if (!list.isEmpty()) {
				lookahead = list.get(0);
			} else {
				lookahead = null;
			}
		} else {
			throw new RuntimeException("mulop FAIL"); // FAIL
		}
	}
	/**
	* Remove ID, then remove left parenthesis and call arguments()
	* @throws RuntimeException
	*/
	public void namedval() {
		if (lookahead.getType() == TokenType.ID) {
			list.remove(0);
			
			// Check if the list is empty and set lookahead
			if (!list.isEmpty()) {
				lookahead = list.get(0);
			} else {
				lookahead = null;
			}
		} else {
			throw new RuntimeException("namedval ID FAIL");
		}
		
		// Call arguments() if lookahead is left parenthesis
		if (lookahead != null && lookahead.getType() == TokenType.LEFT_PARENTHESIS) {
			list.remove(0);
			
			// Check if the list is empty and set lookahead
			if (!list.isEmpty()) {
				lookahead = list.get(0);
			} else {
				lookahead = null;
			}
			arguments();
			
			// Ends with right parenthesis
			if (lookahead != null && lookahead.getType() == TokenType.RIGHT_PARENTHESIS) {
				list.remove(0);
				
				// Check if the list is empty and set lookahead
				if (!list.isEmpty()) {
					lookahead = list.get(0);
				} else {
					lookahead = null;
				}
			} else {
				throw new RuntimeException("Right Parenthesis FAIL");
			}
		}
	}
	
	/**
	* Call arglist if lookahead is ID
	*/
	public void arguments() {
		if (lookahead != null && lookahead.getType() == TokenType.ID) {
			arglist();
		}
	}
	
	/**
	* Remove ID, set lookahead and call arglist()
	* @throws RuntimeException
	*/
	public void arglist() {
		if (lookahead.getType() == TokenType.ID) {
			list.remove(0);
			
			// Check if the list is empty and set lookahead
			if (!list.isEmpty()) {
				lookahead = list.get(0);
			} else {
				lookahead = null;
			}
		} else {
			throw new RuntimeException("arglist FAIL");
		}
		if (lookahead != null && lookahead.getType() == TokenType.COMMA) {
			list.remove(0);
			
			// Check if the list is empty and set lookahead
			if (!list.isEmpty()) {
				lookahead = list.get(0);
			} else {
				lookahead = null;
			}
			arglist();
		}
	}
}