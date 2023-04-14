package org.example.interpreter;

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

    @Override
    public Object visitIdentifier(TestLangParser.IdentifierContext ctx) {
        String variableName = ctx.ID().getText();

        if (!context.isVariableDeclared(variableName)) {
            //TODO change this part and handle the error ""Variable '" + variableName + "' is not declared."
            return null;
        }

        return context.getVariable(variableName);
    }

    @Override
    public Object visitOutExpr(TestLangParser.OutExprContext ctx) {
        Object value = visit(ctx.expr());
        System.out.println(value);
        return value;
    }

}
