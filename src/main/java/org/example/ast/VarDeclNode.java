package org.example.ast;

public class VarDeclNode extends StmtNode{
    private final String identifier;
    private final ExprNode expression;

    public VarDeclNode(String identifier, ExprNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ExprNode getExpression() {
        return expression;
    }
}
