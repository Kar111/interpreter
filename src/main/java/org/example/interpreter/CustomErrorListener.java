package org.example.interpreter;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.example.editor.InterpreterResponse;
import org.example.editor.ResponseStatus;

import java.util.ArrayList;

public class CustomErrorListener extends BaseErrorListener {
    private final ArrayList<InterpreterResponse> interpreterResponses;

    public CustomErrorListener(ArrayList<InterpreterResponse> interpreterResponses) {
        this.interpreterResponses = interpreterResponses;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        interpreterResponses.add(new InterpreterResponse(ResponseStatus.ERROR, "line " + (line - 1) + ":" + charPositionInLine + " " + msg));
    }

}
