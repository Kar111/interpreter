package org.example.ast;

import java.util.List;

public class ProgramNode extends AstNode{
    private final List<StmtNode> statements;

    public ProgramNode(List<StmtNode> statements) {
        this.statements = statements;
    }

    public List<StmtNode> getStatements() {
        return statements;
    }
}
