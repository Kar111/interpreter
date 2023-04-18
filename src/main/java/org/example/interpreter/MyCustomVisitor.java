package org.example.interpreter;

import org.example.ast.*;
import org.example.testLang.TestLangBaseVisitor;
import org.example.testLang.TestLangParser;

import java.util.ArrayList;
import java.util.List;

public class MyCustomVisitor extends TestLangBaseVisitor<Object> {
    private final EvaluationContext context;

    public EvaluationContext getEvaluationContext() {
        return context;
    }

    public MyCustomVisitor() {
        this.context = new EvaluationContext();
    }

    @Override
    public ProgramNode visitProgram(TestLangParser.ProgramContext ctx) {
        List<StmtNode> stmtNodes = new ArrayList<>();

        for (TestLangParser.StmtContext stmtCtx : ctx.stmt()) {
            StmtNode stmtNode = (StmtNode) visit(stmtCtx);
            stmtNodes.add(stmtNode);
        }

        return new ProgramNode(stmtNodes);
    }

    @Override
    public VarDeclNode visitVarDecl(TestLangParser.VarDeclContext ctx) {
        String variableName = ctx.ID().getText();
        ExprNode exprNode = (ExprNode) visit(ctx.expr());

        return new VarDeclNode(variableName, exprNode);
    }

    @Override
    public NumberLiteralNode visitNumberLiteral(TestLangParser.NumberLiteralContext ctx) {
        String numberText = ctx.NUMBER().getText();
        Number value;

        if (numberText.contains(".")) {
            value = Double.parseDouble(numberText);
        } else {
            value = Integer.parseInt(numberText);
        }

        return new NumberLiteralNode(value);
    }

    @Override
    public IdentifierNode visitIdentifier(TestLangParser.IdentifierContext ctx) {
        String variableName = ctx.ID().getText();
        return new IdentifierNode(variableName);
    }

    @Override
    public OutExprNode visitOutExpr(TestLangParser.OutExprContext ctx) {
        ExprNode exprNode = (ExprNode) visit(ctx.expr());
        return new OutExprNode(exprNode);
    }

    @Override
    public PrintStringNode visitPrintString(TestLangParser.PrintStringContext ctx) {
        String str = ctx.STRING().getText();
        str = str.substring(1, str.length() - 1); // Remove "s
        return new PrintStringNode(str);
    }

    @Override
    public BinaryOpNode visitMulDivExpr(TestLangParser.MulDivExprContext ctx) {
        ExprNode left = (ExprNode) visit(ctx.expr(0));
        ExprNode right = (ExprNode) visit(ctx.expr(1));

        if (ctx.mulDivOp.getText().equals("*")) {
            return new MulNode(left, right);
        } else {
            return new DivNode(left, right);
        }
    }

    @Override
    public BinaryOpNode visitPowExpr(TestLangParser.PowExprContext ctx) {
        ExprNode base = (ExprNode) visit(ctx.expr(0));
        ExprNode exponent = (ExprNode) visit(ctx.expr(1));

        return new PowNode(base, exponent);
    }

    @Override
    public ParenExprNode visitParenExpr(TestLangParser.ParenExprContext ctx) {
        ExprNode innerExpr = (ExprNode) visit(ctx.expr());
        return new ParenExprNode(innerExpr);
    }


    @Override
    public BinaryOpNode visitAddSubExpr(TestLangParser.AddSubExprContext ctx) {
        ExprNode left = (ExprNode) visit(ctx.expr(0));
        ExprNode right = (ExprNode) visit(ctx.expr(1));

        if (ctx.addSubOp.getText().equals("+")) {
            return new AddNode(left, right);
        } else {
            return new SubNode(left, right);
        }
    }

    @Override
    public ExprNode visitRangeExpr(TestLangParser.RangeExprContext ctx) {
        ExprNode left = (ExprNode) visit(ctx.expr(0));
        ExprNode right = (ExprNode) visit(ctx.expr(1));

        return new RangeExprNode(left, right);
    }

    @Override
    public ExprNode visitMapExpression(TestLangParser.MapExpressionContext ctx) {
        if (!(ctx.mapExpr().expr(0) instanceof TestLangParser.RangeExprContext)) {
            throw new InterpreterException("First argument must be a range expression: {expr1, expr2}");
        }
        ExprNode range = (ExprNode) visit(ctx.mapExpr().expr(0));
        String lambdaVarName = ctx.mapExpr().ID().getText();
        ExprNode lambdaBody = (ExprNode) visit(ctx.mapExpr().expr(1));

        return new MapExprNode(range, lambdaVarName, lambdaBody);
    }

    /**
     * Visits a 'reduce' expression in the parse tree and creates an instance of {ReduceExprNode}.
     * This method processes the range expression, initial value, variable names, and lambda body for
     * the reduce operation and returns a ReduceExprNode object that represents the entire
     * reduce expression.
     *
     * @param ctx The TestLangParser.ReduceExpressionContext object representing the 'reduce' expression
     *            in the parse tree.
     * @return A ReduceExprNode object representing the 'reduce' expression in the AST.
     */
    @Override
    public ExprNode visitReduceExpression(TestLangParser.ReduceExpressionContext ctx) {
        ExprNode range = (ExprNode) visit(ctx.reduceExpr().expr(0)); // Evaluate the range expression
        ExprNode initialValue = (ExprNode) visit(ctx.reduceExpr().expr(1));

        String lambdaVarName1 = ctx.reduceExpr().ID(0).getText();
        String lambdaVarName2 = ctx.reduceExpr().ID(1).getText();
        ExprNode lambdaBody = (ExprNode) visit(ctx.reduceExpr().expr(2));

        return new ReduceExprNode(range, initialValue, lambdaVarName1, lambdaVarName2, lambdaBody);
    }


}
