# TestLang Interpreter
 
TestLang is a simple, custom programming language designed for educational purposes. The interpreter is built using ANTLR4 for parsing and Java for execution.

**Features**
TestLang supports the following features:

- Variable declaration and assignment
- Arithmetic operations
- Lambda functions
- Map and reduce operations on sequences
- Output statements for displaying results

**Prerequisites**
 To build and run the TestLang interpreter, you will need:

- Java Development Kit (JDK) 8 or later
- Maven

**Building the Project**

In order to run it from an IDE
- Run main function inside org.example/Main

**Example TestLang Code**
Here's an example of TestLang code that calculates an approximation of pi:
```var n = 500
var sequence = map({0, n}, i -> (-1)^i / (2.0 * i + 1))
var pi = 4 * reduce(sequence, 0, x y -> x + y)
print "pi = "
out pi```
