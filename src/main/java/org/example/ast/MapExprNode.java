package org.example.ast;

public class MapExprNode extends ExprNode{
    private final ExprNode sequence;
    private final String identifier;
    private final ExprNode body;

    public MapExprNode(ExprNode sequence, String identifier, ExprNode body) {
        this.sequence = sequence;
        this.identifier = identifier;
        this.body = body;
    }

    public ExprNode getSequence() {
        return sequence;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ExprNode getBody() {
        return body;
    }
}
