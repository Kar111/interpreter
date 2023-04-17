package org.example.editor;

public class InterpreterService {
    public static InterpreterResponse[] get_response(String input) {
        // TODO: process input
        InterpreterResponse[] response = {
                new InterpreterResponse(ResponseStatus.ERROR, "SyntaxError: ..."),
                new InterpreterResponse(ResponseStatus.SUCCESS, "my output"),
        };
        return response;
    }
}
