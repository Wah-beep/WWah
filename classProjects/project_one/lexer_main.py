# lexer_main.py
# Wah Saw Tamalar
# Read from source.txt
# Output to source_tokens.txt
#
# Usage: python3 lexer_main.py source
#

import sys
import lexer

def main():
    """Get the file name using sys.argv[1]
    
    The file is to read and write out. Import lexer.py 
    and called lexer with input and output parameters to read and write out.
    """
    the_filename = sys.argv[1]
    input_read = open(the_filename + ".txt", "r")
    output_write = open(the_filename + "_tokens.txt", "w")
    lexer.lexer(input_read, output_write)
    input_read.close()
    output_write.close()
    print("Done! Check the " + the_filename + "_tokens")

if __name__ == "__main__":
    main()