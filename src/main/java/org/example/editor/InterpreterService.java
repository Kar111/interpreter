package org.example.editor;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.interpreter.MyCustomVisitor;
import org.example.testLang.TestLangLexer;
import org.example.testLang.TestLangParser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InterpreterService {
    public static InterpreterResponse[] get_response(String input) {

        CharStream charStream = CharStreams.fromString(input);
        TestLangLexer lexer = new TestLangLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TestLangParser parser = new TestLangParser(tokens);
        ParseTree tree = parser.program();
        MyCustomVisitor visitor = new MyCustomVisitor();

        // Save the original System.out
        PrintStream originalOut = System.out;
        // Create a custom output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream customOut = new PrintStream(outputStream);

        // Set System.out to the custom output stream
        System.setOut(customOut);

        // Execute the visitor on the parse tree
        visitor.visit(tree);

        // Reset System.out to the original output stream
        System.setOut(originalOut);

        // Get the output as a string
        String output = outputStream.toString();

        return new InterpreterResponse[]{
                new InterpreterResponse(ResponseStatus.ERROR, "SyntaxError: ..."),
                new InterpreterResponse(ResponseStatus.SUCCESS, output),
        };
    }
}
