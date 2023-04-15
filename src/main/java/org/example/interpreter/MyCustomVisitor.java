package org.example.interpreter;

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
        //TODO just return this
        System.out.println(value);
        return value;
    }

    @Override
    public Object visitPrintString(TestLangParser.PrintStringContext ctx) {
        String str = ctx.STRING().getText();
        str = str.substring(1, str.length() - 1); // Remove "s
        //TODO just return this
        System.out.print(str);
        return null;
    }

    @Override
    public Object visitMulDivExpr(TestLangParser.MulDivExprContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));

        if (!(left instanceof Number) || !(right instanceof Number)) {
          //ToDo Operands must be numbers for multiplication and division;
        }

        Number leftNum = (Number) left;
        Number rightNum = (Number) right;

        if (ctx.mulDivOp.getText().equals("*")) {
            return leftNum.doubleValue() * rightNum.doubleValue();
        } else {
            if (rightNum.doubleValue() == 0) {
                //TODO "Division by zero is not allowed";
            }
            return leftNum.doubleValue() / rightNum.doubleValue();
        }
    }

    @Override
    public Object visitPowExpr(TestLangParser.PowExprContext ctx) {
        //TODO get rid of the duplicate code
        Object base = visit(ctx.expr(0));
        Object exponent = visit(ctx.expr(1));

        if (!(base instanceof Number) || !(exponent instanceof Number)) {
            //TODO "Operands must be numbers for exponentiation");
        }

        Number baseNum = (Number) base;
        Number exponentNum = (Number) exponent;

        return Math.pow(baseNum.doubleValue(), exponentNum.doubleValue());
    }

    @Override
    public Object visitParenExpr(TestLangParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Object visitAddSubExpr(TestLangParser.AddSubExprContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));

        if (!(left instanceof Number) || !(right instanceof Number)) {
            //TODO "Invalid operands for addition or subtraction.";
        }

        Number leftNumber = (Number) left;
        Number rightNumber = (Number) right;

        if (ctx.addSubOp.getText().equals("+")) {
            return leftNumber.doubleValue() + rightNumber.doubleValue();
        } else {
            return leftNumber.doubleValue() - rightNumber.doubleValue();
        }
    }

    @Override
    public Object visitRangeExpr(TestLangParser.RangeExprContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));

        if (!(left instanceof Integer) || !(right instanceof Integer)) {
            //TODO "Only integers are allowed in range expressions.";
        }

        Integer leftNumber = (Integer) left;
        Integer rightNumber = (Integer) right;

        if (rightNumber < leftNumber) {
            //TODO "Right number must not be less than left number in a range expression.";
        }

        List<Integer> range = new ArrayList<>();
        for (int i = leftNumber; i <= rightNumber; i++) {
            range.add(i);
        }

        return range;
    }


}
