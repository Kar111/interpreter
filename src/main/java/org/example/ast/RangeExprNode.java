package org.example.ast;

public class RangeExprNode extends ExprNode {
    private final ExprNode expr1;
    private final ExprNode expr2;

    public RangeExprNode(ExprNode expr1, ExprNode expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public ExprNode getExpr1() {
        return expr1;
    }

    public ExprNode getExpr2() {
        return expr2;
    }
}
