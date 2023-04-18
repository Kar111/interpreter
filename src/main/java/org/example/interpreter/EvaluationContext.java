package org.example.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class EvaluationContext {
    private final Deque<Map<String, Object>> variableScope;

    public EvaluationContext() {

        this.variableScope = new ArrayDeque<>();
        this.variableScope.push(new HashMap<>());
    }

    public void enterScope() {

        variableScope.push(new HashMap<>());
    }

    public void exitScope() {

        variableScope.pop();
    }

    public void setVariable(String name, Object value) {

        assert variableScope.peek() != null;
        variableScope.peek().put(name, value);
    }

    public Object getVariable(String name) {

        Map<String, Object> scope = variableScope.peek();
        assert scope != null;
        if (scope.containsKey(name)) {
            return scope.get(name);
        }

        return null;
    }

    public boolean isVariableDeclared(String name) {
        return variableScope.stream().anyMatch(scope -> scope.containsKey(name));
    }
}
