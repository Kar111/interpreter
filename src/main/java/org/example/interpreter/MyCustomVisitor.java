package org.example.interpreter;

import org.example.testLang.TestLangBaseVisitor;
import org.example.testLang.TestLangParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    /**
     * Evaluates a map expression, applying a given lambda function to each element in a sequence.
     *
     * <p>The method first evaluates the range expression, ensuring it is a valid sequence.
     * Then, it enters a new scope for the lambda function and creates a lambda function that maps
     * each element in the sequence. The lambda function uses the provided variable name to store
     * the input value for each element in the sequence.
     *
     * <p>After mapping the sequence, the method exits the lambda function scope and returns the
     * mapped sequence.
     *
     * @param ctx The MapExpressionContext object provided by the ANTLR parser.
     * @return The mapped sequence after applying the lambda function to each element in the sequence.
     */
    @Override
    public Object visitMapExpression(TestLangParser.MapExpressionContext ctx) {
        Object range = visit(ctx.mapExpr().expr(0)); // Evaluate the range expression

        if (!(range instanceof List)) {
           //TODO "Map function expects a sequence as the first argument.";
        }

        List<Object> sequence = (List<Object>) range;

        context.enterScope();

        String lambdaVarName = ctx.mapExpr().ID().getText();

        Function<Object, Object> lambdaFunction = (input) -> {
            context.setVariable(lambdaVarName, input);
            return visit(ctx.mapExpr().expr(1));
        };

        List<Object> mappedSequence = sequence.stream().map(lambdaFunction).collect(Collectors.toList());

        context.exitScope();

        return mappedSequence;
    }

}
