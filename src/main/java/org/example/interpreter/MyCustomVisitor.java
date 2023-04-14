package org.example.interpreter;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.example.testLang.TestLangBaseVisitor;
import org.example.testLang.TestLangParser;

public class MyCustomVisitor extends TestLangBaseVisitor<Object> {
    private final EvaluationContext context;

    public EvaluationContext getEvaluationContext() {
        return context;
    }

    public MyCustomVisitor() {
        this.context = new EvaluationContext();

    }

    @Override
    public Object visitTerminal(TerminalNode node) {
        if (node.getSymbol().getType() == TestLangParser.VAR) {
            return visitVarDecl((TestLangParser.VarDeclContext) node.getParent());
        } else if (node.getSymbol().getType() == TestLangParser.NUMBER) {
            return visitNumberLiteral((TestLangParser.NumberLiteralContext)node.getParent());
        }
        return super.visitTerminal(node);
    }
    @Override
    public Object visitProgram(TestLangParser.ProgramContext ctx) {

        return visitChildren(ctx);
    }
    @Override
    public Object visitVarDecl(TestLangParser.VarDeclContext ctx) {
        String variableName = ctx.ID().getText();
        Object value = visit(ctx.expr());

        // TODO Check if the variable name is already declared in the current scope

        context.setVariable(variableName, value);
        return value;
    }

    @Override
    public Object visitNumberLiteral(TestLangParser.NumberLiteralContext ctx) {
        String numberText = ctx.NUMBER().getText();

        if (numberText.contains(".")) {
            return Double.parseDouble(numberText);
        } else {
            return Integer.parseInt(numberText);
        }
    }

}
