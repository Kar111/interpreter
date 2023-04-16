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
import java.util.Arrays;

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
    public void visitNumberLiteral() {
        //SETUP
        String input = "var myVar = 11.6";
        MyCustomVisitor visitor = prepareTest(input);

        //EXECUTE
        EvaluationContext context = visitor.getEvaluationContext();
        Object value = context.getVariable("myVar");

        //ASSERT
        assertEquals(11.6, value);

    }

    @Test
    public void visitMulDivExpr() {
        //SETUP
        String input = "var myVar = 11 * 4";
        MyCustomVisitor visitor = prepareTest(input);

        //EXECUTE
        EvaluationContext context = visitor.getEvaluationContext();
        Object value = context.getVariable("myVar");

        //ASSERT
        assertEquals(11 * 4.0, value);

    }

    @Test
    public void visitAddSubExpr() {
        //SETUP
        String input = "var myVar = 11 + 4";
        MyCustomVisitor visitor = prepareTest(input);

        //EXECUTE
        EvaluationContext context = visitor.getEvaluationContext();
        Object value = context.getVariable("myVar");

        //ASSERT
        assertEquals(11 + 4.0, value);

    }

    @Test
    public void visitPowExpr() {
        //SETUP
        String input = "var myVar = 2 ^ 4";
        MyCustomVisitor visitor = prepareTest(input);

        //EXECUTE
        EvaluationContext context = visitor.getEvaluationContext();
        Object value = context.getVariable("myVar");

        //ASSERT
        assertEquals(Math.pow(2, 4), value);

    }
    @Test
    public void visitMapExpression() {
        //SETUP
        String input = "var seq = map({0, 2}, i -> i * 2)";
        MyCustomVisitor visitor = prepareTest(input);

        //EXECUTE
        EvaluationContext context = visitor.getEvaluationContext();
        Object value = context.getVariable("seq");

        //ASSERT
        assertEquals(Arrays.asList(0.0, 2.0, 4.0), value);

    }

    @Test
    public void visitReduceExpression() {
        //SETUP
        String input = "var seq = map({0, 2}, i -> i * 2)";
        MyCustomVisitor visitor = prepareTest(input);

        //EXECUTE
        EvaluationContext context = visitor.getEvaluationContext();
        Object value = context.getVariable("seq");

        //ASSERT
        assertEquals(Arrays.asList(0.0, 2.0, 4.0), value);

    }

    @Test
    public void visitRangeExpr() {
        //SETUP
        String input = "var range = {1,4}";
        MyCustomVisitor visitor = prepareTest(input);

        //EXECUTE
        EvaluationContext context = visitor.getEvaluationContext();
        Object value = context.getVariable("range");

        //ASSERT
        assertEquals(Arrays.asList(1, 2, 3, 4), value);
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

    @Test
    public void visitPrintString() {

        //SETUP
        String input = "print \"hello world\"";
        CharStream charStream = CharStreams.fromString(input);
        TestLangLexer lexer = new TestLangLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TestLangParser parser = new TestLangParser(tokens);
        ParseTree tree = parser.program();
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
        assertEquals("hello world", output.trim());
    }

}