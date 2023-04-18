package org.example.ast;

public class ReduceExprNode extends ExprNode{
    private final ExprNode sequence;
    private final ExprNode neutralElement;
    private final String identifier1;
    private final String identifier2;
    private final ExprNode body;

    public ReduceExprNode(ExprNode sequence, ExprNode neutralElement, String identifier1, String identifier2, ExprNode body) {
        this.sequence = sequence;
        this.neutralElement = neutralElement;
        this.identifier1 = identifier1;
        this.identifier2 = identifier2;
        this.body = body;
    }

    public ExprNode getSequence() {
        return sequence;
    }

    public ExprNode getNeutralElement() {
        return neutralElement;
    }

    public String getIdentifier1() {
        return identifier1;
    }

    public String getIdentifier2() {
        return identifier2;
    }

    public ExprNode getBody() {
        return body;
    }
}
