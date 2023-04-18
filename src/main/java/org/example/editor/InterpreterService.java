package org.example.editor;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.interpreter.InterpreterException;
import org.example.interpreter.MyCustomVisitor;
import org.example.interpreter.CustomErrorListener;
import org.example.testLang.TestLangLexer;
import org.example.testLang.TestLangParser;

import java.util.ArrayList;

public class InterpreterService {
    public static InterpreterResponse[] get_response(String input) {

        ArrayList<InterpreterResponse> interpreterResponses = new ArrayList<>();
        CustomErrorListener customErrorListener = new CustomErrorListener();
        try {
        CharStream charStream = CharStreams.fromString(input);
        TestLangLexer lexer = new TestLangLexer(charStream);
        lexer.removeErrorListeners(); // Remove the default error listeners
        lexer.addErrorListener(customErrorListener);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TestLangParser parser = new TestLangParser(tokens);
        parser.removeErrorListeners(); // Remove the default error listeners
        parser.addErrorListener(customErrorListener);
        ParseTree tree = parser.program();
        MyCustomVisitor visitor = new MyCustomVisitor(interpreterResponses);
            // Execute the visitor on the parse tree
            visitor.visit(tree);
        } catch (InterpreterException e) {
            interpreterResponses.add(new InterpreterResponse(ResponseStatus.ERROR, e.getMessage()));
        }

        InterpreterResponse[] interpreterResponsesArray = new InterpreterResponse[interpreterResponses.size()];
        return interpreterResponses.toArray(interpreterResponsesArray);
    }
}
