package org.example.ast;

public class ParenExprNode extends ExprNode{
    private final ExprNode innerExpr;

    public ParenExprNode(ExprNode innerExpr) {
        this.innerExpr = innerExpr;
    }

    public ExprNode getInnerExpr() {
        return innerExpr;
    }
}
