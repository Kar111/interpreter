package org.example.ast;

public class OutExprNode extends StmtNode{
    private final ExprNode expression;

    public OutExprNode(ExprNode expression) {
        this.expression = expression;
    }

    public ExprNode getExpression() {
        return expression;
    }
}
