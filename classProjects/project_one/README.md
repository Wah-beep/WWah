# Python Lexer Project One Readme

This project is a simple lexer function that read from a file,
and output the content to a file ends with "_tokens".
lexer_main.py import lexer and calls the function lexer().
Lexer() read from the input file and write out each token with type in a new line to output file. 

In this folder:\
	.gitignore							&emsp;&emsp;&emsp;&emsp;- Git ignore file\
	state-diagram.pdf					&emsp;&emsp;&emsp;&emsp;- The state machine diagram\
	README.md							&emsp;&emsp;&emsp;&emsp;- README file\
	lexer.py							&emsp;&emsp;&emsp;&emsp;- Lexer function\
	lexer_main.py						&emsp;&emsp;&emsp;&emsp;- Python program to read and write\
	source.txt							&emsp;&emsp;&emsp;&emsp;- Read from this file\
	source2.txt							&emsp;&emsp;&emsp;&emsp;- Read from this file 2
	
<ins>How-To-Use</ins>\
Run this command line in Bash:
>python3 lexer_main.py [filename]

__state-diagram\
Hand drawing of state diagram

__source & source2\
Input files, each source has different test cases.
One file with spaces and the other without spaces.

__.gitignore\
Ignore __pycache__, source_tokens.txt, and source2_tokens.txt

Author: Wah Saw Tamalar