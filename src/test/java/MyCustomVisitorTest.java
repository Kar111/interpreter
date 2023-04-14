import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.interpreter.EvaluationContext;
import org.example.testLang.TestLangLexer;
import org.example.testLang.TestLangParser;
import org.example.interpreter.MyCustomVisitor;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MyCustomVisitorTest {

    private MyCustomVisitor prepareTest(String input) {
        CharStream charStream = CharStreams.fromString(input);
        TestLangLexer lexer = new TestLangLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TestLangParser parser = new TestLangParser(tokens);
        ParseTree tree = parser.program();
        MyCustomVisitor visitor = new MyCustomVisitor();
        visitor.visit(tree);
        return visitor;
    }

    @Test
    public void visitVarDecl() {
            //SETUP
        String input = "var myVar = 11";
        MyCustomVisitor visitor = prepareTest(input);

            //EXECUTE
        EvaluationContext context = visitor.getEvaluationContext();
        Object value = context.getVariable("myVar");

            //ASSERT
        assertEquals(11, value);

    }

    @Test
    public void visitOutExpr() {

        //SETUP
        String input = "var v = 11 \n out v";
        CharStream charStream = CharStreams.fromString(input);
        TestLangLexer lexer = new TestLangLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TestLangParser parser = new TestLangParser(tokens);
        ParseTree tree = parser.program();
        System.out.println(tree.toStringTree(parser));
        MyCustomVisitor visitor = new MyCustomVisitor();

        //EXECUTE

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

        //ASSERT
        assertEquals("11", output.trim());
    }
}