## Java Unit-testing and Parsing
The project reads the lexeme and its token type per line.

In this folder:\
.gitignore	&emsp;&emsp;&emsp;&emsp;- Git ignore file\
junit-platform-console-standalone-6.0.2 &emsp;&emsp;&emsp;&emsp;- For units testing\
README	&emsp;&emsp;&emsp;&emsp;-This file\
Token.java	&emsp;&emsp;&emsp;&emsp;- Set Token\
TokenReader.java	&emsp;&emsp;&emsp;&emsp;- Read lexeme and token type\
TokenReaderTest.java 	&emsp;&emsp;&emsp;&emsp;- Unit testing\
TokenTest 	&emsp;&emsp;&emsp;&emsp;- Token test\
TokenType	&emsp;&emsp;&emsp;&emsp;- Token type, enum\

<ins>How-To-Use<ins>\
Run this command line in Bash:
>javac -cp "./junit-platform-console-standalone-6.0.2.jar;." Token.java TokenReader.java TokenReaderTest.java\
>java -jar ./junit-platform-console-standalone-6.0.2.jar execute -cp . --scan-classpath .

Author: Wah Saw Tamalar