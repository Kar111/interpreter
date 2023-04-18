package org.example.ast;

public class IdentifierNode extends ExprNode{
    private final String name;

    public IdentifierNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
