import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.ast.ProgramNode;
import org.example.editor.InterpreterResponse;
import org.example.interpreter.AstEvaluator;
import org.example.interpreter.EvaluationContext;
import org.example.interpreter.MyCustomVisitor;
import org.example.testLang.TestLangLexer;
import org.example.testLang.TestLangParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyCustomVisitorTest {

    private AstEvaluator prepareTest(String input, ArrayList<InterpreterResponse> interpreterResponses) {
        // Create the lexer and parser
        CharStream charStream = CharStreams.fromString(input);
        TestLangLexer lexer = new TestLangLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TestLangParser parser = new TestLangParser(tokens);

        // Parse the input and create the AST
        ParseTree tree = parser.program();
        MyCustomVisitor visitor = new MyCustomVisitor();
        ProgramNode programNode = (ProgramNode) visitor.visit(tree);

        // Evaluate the AST
        AstEvaluator astEvaluator = new AstEvaluator(interpreterResponses);
        astEvaluator.evaluateProgram(programNode);
        return astEvaluator;
    }

    @Test
    public void visitVarDecl() {
        //SETUP
        String input = "var myVar = 11";
        AstEvaluator astEvaluator = prepareTest(input, new ArrayList<>());

        //EXECUTE
        EvaluationContext context = astEvaluator.getEvaluationContext();
        Object value = context.getVariable("myVar");

        //ASSERT
        assertEquals(11, value);

    }

    @Test
    public void visitNumberLiteral() {
        //SETUP
        String input = "var myVar = 11.6";
        AstEvaluator astEvaluator = prepareTest(input, new ArrayList<>());

        //EXECUTE
        EvaluationContext context = astEvaluator.getEvaluationContext();
        Object value = context.getVariable("myVar");

        //ASSERT
        assertEquals(11.6, value);

    }

    @Test
    public void visitMulDivExpr() {
        //SETUP
        String input = "var myVar = 11 * 4";
        AstEvaluator astEvaluator = prepareTest(input, new ArrayList<>());

        //EXECUTE
        EvaluationContext context = astEvaluator.getEvaluationContext();
        Object value = context.getVariable("myVar");

        //ASSERT
        assertEquals(11 * 4.0, value);

    }

    @Test
    public void visitAddSubExpr() {
        //SETUP
        String input = "var myVar = 11 + 4";
        AstEvaluator astEvaluator = prepareTest(input, new ArrayList<>());

        //EXECUTE
        EvaluationContext context = astEvaluator.getEvaluationContext();
        Object value = context.getVariable("myVar");

        //ASSERT
        assertEquals(11 + 4.0, value);

    }

    @Test
    public void visitPowExpr() {
        //SETUP
        String input = "var myVar = 2 ^ 4";
        AstEvaluator astEvaluator = prepareTest(input, new ArrayList<>());

        //EXECUTE
        EvaluationContext context = astEvaluator.getEvaluationContext();
        Object value = context.getVariable("myVar");

        //ASSERT
        assertEquals(Math.pow(2, 4), value);

    }

    @Test
    public void visitMapExpression() {
        //SETUP
        String input = "var seq = map({0, 2}, i -> i * 2)";
        AstEvaluator astEvaluator = prepareTest(input, new ArrayList<>());

        //EXECUTE
        EvaluationContext context = astEvaluator.getEvaluationContext();
        Object value = context.getVariable("seq");

        //ASSERT
        assertEquals(Arrays.asList(0.0, 2.0, 4.0), value);

    }

    @Test
    public void visitReduceExpressionWhichTakesRange() {
        //SETUP
        String input = "var seq = reduce({5, 7}, 1, x y -> x * y)";
        AstEvaluator astEvaluator = prepareTest(input, new ArrayList<>());

        //EXECUTE
        EvaluationContext context = astEvaluator.getEvaluationContext();
        Object value = context.getVariable("seq");

        //ASSERT
        assertEquals(1.0 * 5 * 6 * 7, value);

    }

    @Test
    public void visitReduceExpressionWhichTakesIdentifier() {
        //SETUP
        String input = "var seq = map({1, 3}, i -> i * 2) \n" +
                "var reducedSeq = reduce(seq, 1, x y -> x * y)";
        AstEvaluator astEvaluator = prepareTest(input, new ArrayList<>());

        //EXECUTE
        EvaluationContext context = astEvaluator.getEvaluationContext();
        Object value = context.getVariable("reducedSeq");

        //ASSERT
        assertEquals(48.0, value);

    }

    @Test
    public void visitRangeExpr() {
        //SETUP
        String input = "var range = {1,4}";
        AstEvaluator astEvaluator = prepareTest(input, new ArrayList<>());

        //EXECUTE
        EvaluationContext context = astEvaluator.getEvaluationContext();
        Object value = context.getVariable("range");

        //ASSERT
        assertEquals(Arrays.asList(1, 2, 3, 4), value);
    }

    @Test
    public void visitOutExpr() {

        //SETUP
        String input = "var v = 11 \n out v";
        ArrayList<InterpreterResponse> interpreterResponses = new ArrayList<>();

        //EXECUTE
        prepareTest(input, interpreterResponses);

        //ASSERT
        assertEquals("11" + "\n", interpreterResponses.get(0).message());
    }

    @Test
    public void visitPrintString() {

        //SETUP
        String input = "print \"hello world\"";
        ArrayList<InterpreterResponse> interpreterResponses = new ArrayList<>();

        //EXECUTE
        prepareTest(input, interpreterResponses);

        //ASSERT
        assertEquals("hello world", interpreterResponses.get(0).message());
    }

    @Test
    public void testTheCodeForGivenExample() {

        //SETUP
        String input = """
                var n = 500
                var sequence = map({0, n}, i -> (-1)^i / (2.0 * i + 1))
                var pi = 4 * reduce(sequence, 0, x y -> x + y)
                print "pi = "
                out pi""";
        ArrayList<InterpreterResponse> interpreterResponses = new ArrayList<>();

        //EXECUTE
        prepareTest(input, interpreterResponses);

        //ASSERT
        assertEquals("pi = 3.143588659585789\n", interpreterResponses.get(0).message() + interpreterResponses.get(1).message());
    }

    @ParameterizedTest
    @MethodSource("arithmeticalExpressions")
    void testArithmeticalExpression(String input, double expectedValue) {
        AstEvaluator astEvaluator = prepareTest(input, new ArrayList<>());

        //EXECUTE
        EvaluationContext context = astEvaluator.getEvaluationContext();
        Object value = context.getVariable("myVar");

        //ASSERT
        assertEquals(expectedValue, value);
    }

    static Stream<Arguments> arithmeticalExpressions() {
        return Stream.of(
                Arguments.of("var myVar = 3 + 4 * 2.0 - 6 / 2", 3 + 4 * 2.0 - 6 / 2),
                Arguments.of("var myVar = 3 + 4 * 2 - 6.0 / (2 ^ 2)", 3 + 4 * 2 - 6.0 / Math.pow(2, 2)),
                Arguments.of("var myVar = (3 + 4) * 2 - 6.0 / (2 ^ 2)", (3 + 4) * 2 - 6.0 / Math.pow(2, 2))
        );
    }

}