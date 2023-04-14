import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.interpreter.EvaluationContext;
import org.example.testLang.TestLangLexer;
import org.example.testLang.TestLangParser;
import org.example.interpreter.MyCustomVisitor;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyCustomVisitorTest {

    @Test
    public void visitVarDecl() {
            //SETUP
            String input = "var myVar = 11";
            CharStream charStream = CharStreams.fromString(input);
            TestLangLexer lexer = new TestLangLexer(charStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TestLangParser parser = new TestLangParser(tokens);
            ParseTree tree = parser.stmt();
            MyCustomVisitor visitor = new MyCustomVisitor();

            //EXECUTE
            visitor.visit(tree);
            EvaluationContext context = visitor.getEvaluationContext();
            Object value = context.getVariable("myVar");

            //ASSERT
            assertEquals(11, value);

    }
}