# lexer.py
# Wah Saw Tamalar
# Simple lexer function

def iskeyword(str_input):
    """Check if the string input is in KEYWORDS
    
    Return True if the string is in KEYWORDS.
    Else return False. 
    
    Parameters
    ----------
    str_input: string
    """
    KEYWORDS = {"def", "while", "if", "elif"}
    if str_input in KEYWORDS:
        return True
    else:
        return False

def operator_type(ch):
    """Get operator type
    Check the character is one of the operator and return the type
    
    Return the operator type
    """
    OPERATORS = {
        "+": "ADD",
        "-": "SUBTRACT",
        "*": "MULTIPLY",
        "/": "DIVIDE"
    }
    
    return OPERATORS.get(ch)
    
def lexer(input_file, output_file):
    """Simple lexer function
    
    Read from the input_file and write out each token with its type in a new line to output_file. 
    The states are START, NUM, WORD, and ERROR.
    There are token type of NUMBER, OPERATOR, SYMBOL, ERROR, ID, and KEYWORD.
    Read through each character. Change states. Write out after each token is completed. 
    Using iskeyword function to check if a token is a keyword or id.
    SYMBOLS are check using array. 
    
    Parameters
    ----------
    input_file: file
        Read from this file
    output_file: file
        Write out to this file
    """
    OPERATORS = {"+", "-", "*", "/"}
    SYMBOLS = {"(", ")", "$", "!"}
    curr_token = ""
    state = "START"
    for line in input_file:
        for ch in line:
            if state == "START":
                if ch.isdigit():
                    state = "NUM"
                    curr_token = ch
                    
                elif ch in OPERATORS:
                    op_type = operator_type(ch)
                    output_file.write(ch + "[" + op_type + "]" + "\n")
                    
                elif ch.isspace():
                    pass
                
                elif ch in SYMBOLS:
                    output_file.write(ch + "[SYMBOL]" + "\n")
                
                else:
                    state = "WORD"
                    curr_token = ch
                    
            elif state == "NUM":
                if ch.isdigit():
                    curr_token += ch
                    
                elif ch in OPERATORS:
                    output_file.write(curr_token + "[NUMBER]" + "\n")
                    curr_token = ""
                    state = "START"
                    op_type = operator_type(ch)
                    output_file.write(ch + "[" + op_type + "]" + "\n")
                    
                elif ch.isspace():
                    output_file.write(curr_token + "[NUMBER]" + "\n")
                    curr_token = ""
                    state = "START"
                
                elif ch in SYMBOLS:
                    output_file.write(curr_token + "[NUMBER]" + "\n")
                    curr_token = ""
                    state = "START"
                    output_file.write(ch + "[SYMBOL]" "\n")
                
                else:
                    curr_token += ch
                    state = "ERROR"
                    
            elif state == "WORD":
                if ch in OPERATORS:
                    if iskeyword(curr_token):
                        output_file.write(curr_token + "[KEYWORD]" + "\n")
                        curr_token = ""
                        op_type = operator_type(ch)
                        output_file.write(ch + "[" + op_type + "]" + "\n")
                    else:
                        output_file.write(curr_token + "[ID]" + "\n")
                        curr_token = ""
                        op_type = operator_type(ch)
                        output_file.write(ch + "[" + op_type + "]" + "\n")
                    state = "START"
                
                elif ch.isspace():
                    if iskeyword(curr_token):
                        output_file.write(curr_token + "[KEYWORD]" + "\n")
                        curr_token = ""
                    else:
                        output_file.write(curr_token + "[ID]" + "\n")
                        curr_token = ""
                    state = "START"
                
                elif ch in SYMBOLS:
                    if iskeyword(curr_token):
                        output_file.write(curr_token + "[KEYWORD]" + "\n")
                        curr_token = ""
                        output_file.write(ch + "[SYMBOL]" + "\n")
                    else:
                        output_file.write(curr_token + "[ID]" + "\n")
                        curr_token = ""
                        output_file.write(ch + "[SYMBOL]" + "\n")
                    state = "START"
                
                else:
                    curr_token += ch
                    
            elif state == "ERROR":
                if ch in OPERATORS:
                    output_file.write(curr_token + "[ERROR]" + "\n")
                    curr_token = ""
                    state = "START"
                    op_type = operator_type(ch)
                    output_file.write(ch + "[" + op_type + "]" + "\n")
                
                elif ch in SYMBOLS:
                    output_file.write(curr_token + "[ERROR]" + "\n")
                    curr_token = ""
                    state = "START"
                    output_file.write(ch + "[SYMBOL]" + "\n")
                    
                elif ch.isspace():
                    output_file.write(curr_token + "[ERROR]" + "\n")
                    curr_token = ""
                    state = "START"
                else:
                    curr_token += ch
                    
    if state == "NUM":
        output_file.write(curr_token + "[NUMBER]" + "\n")
        
    elif state == "WORD":
        if iskeyword(curr_token):
            output_file.write(curr_token + "[KEYWORD]" + "\n")
        else:
            output_file.write(curr_token + "[ID]" + "\n")
    
    elif state == "ERROR":
        output_file.write(curr_token + "[ERROR]" + "\n")