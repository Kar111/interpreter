package org.example.ast;

public abstract class  BinaryOpNode extends ExprNode{
    private final ExprNode left;
    private final ExprNode right;

    public BinaryOpNode(ExprNode left, ExprNode right) {
        this.left = left;
        this.right = right;
    }

    public ExprNode getLeft() {
        return left;
    }

    public ExprNode getRight() {
        return right;
    }
}
