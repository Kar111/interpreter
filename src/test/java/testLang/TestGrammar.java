import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.testLang.TestLangLexer;
import org.example.testLang.TestLangParser;

import java.io.FileInputStream;
import java.io.InputStream;

public class TestGrammar {
    public static void main(String[] args) throws Exception {
        InputStream ti = new FileInputStream("src/test/java/testLang/test_input.txt");
        ANTLRInputStream input = new ANTLRInputStream(ti);
        TestLangLexer lexer = new TestLangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TestLangParser parser = new TestLangParser(tokens);
        ParseTree tree = parser.program();
        System.out.println(tree.toStringTree(parser));
    }
}
