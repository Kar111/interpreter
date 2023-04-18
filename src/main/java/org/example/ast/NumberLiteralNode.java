package org.example.ast;

public class NumberLiteralNode extends ExprNode{
    private final Number value;

    public NumberLiteralNode(Number value) {
        this.value = value;
    }

    public Number getValue() {
        return value;
    }
}
