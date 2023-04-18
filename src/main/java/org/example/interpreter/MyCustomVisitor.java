package org.example.interpreter;

import org.example.editor.InterpreterResponse;
import org.example.editor.ResponseStatus;
import org.example.testLang.TestLangBaseVisitor;
import org.example.testLang.TestLangParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MyCustomVisitor extends TestLangBaseVisitor<Object> {
    private final EvaluationContext context;
    private ArrayList<InterpreterResponse> interpreterResponses;

    public EvaluationContext getEvaluationContext() {
        return context;
    }

    public MyCustomVisitor(ArrayList<InterpreterResponse> interpreterResponses) {
        this.context = new EvaluationContext();
        this.interpreterResponses = interpreterResponses;
    }

    @Override
    public Object visitProgram(TestLangParser.ProgramContext ctx) {

        return visitChildren(ctx);
    }

    @Override
    public Object visitVarDecl(TestLangParser.VarDeclContext ctx) {
        String variableName = ctx.ID().getText();
        Object value = visit(ctx.expr());

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
            interpreterResponses.add(new InterpreterResponse(ResponseStatus.ERROR, "Variable '" + variableName + "' is not declared."));
            return null;
        }

        return context.getVariable(variableName);
    }

    @Override
    public Object visitOutExpr(TestLangParser.OutExprContext ctx) {
        Object value = visit(ctx.expr());
        if(value != null) {
            interpreterResponses.add(new InterpreterResponse(ResponseStatus.SUCCESS, value.toString()));
        }
        return value;
    }

    @Override
    public Object visitPrintString(TestLangParser.PrintStringContext ctx) {
        String str = ctx.STRING().getText();
        str = str.substring(1, str.length() - 1); // Remove "s
        interpreterResponses.add(new InterpreterResponse(ResponseStatus.SUCCESS, str));
        return null;
    }

    @Override
    public Object visitMulDivExpr(TestLangParser.MulDivExprContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));

        if (!(left instanceof Number leftNum) || !(right instanceof Number rightNum)) {
            interpreterResponses.add(new InterpreterResponse(ResponseStatus.ERROR, "\"Operands must be numbers for multiplication and division\""));
            return null;
        }

        if (ctx.mulDivOp.getText().equals("*")) {
            return leftNum.doubleValue() * rightNum.doubleValue();
        } else {
            if (rightNum.doubleValue() == 0) {
                interpreterResponses.add(new InterpreterResponse(ResponseStatus.ERROR, "Division by zero is not allowed."));
                return null;
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

            interpreterResponses.add(new InterpreterResponse(ResponseStatus.ERROR, "Operands must be numbers for exponentiation"));
            return null;
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
            interpreterResponses.add(new InterpreterResponse(ResponseStatus.ERROR, "Invalid operands for addition or subtraction, operands should be numbers"));
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
            interpreterResponses.add(new InterpreterResponse(ResponseStatus.ERROR, "Only integers are allowed in range expressions."));
            return null;
        }

        Integer leftNumber = (Integer) left;
        Integer rightNumber = (Integer) right;

        if (rightNumber < leftNumber) {
            interpreterResponses.add(new InterpreterResponse(ResponseStatus.ERROR, "Right number must not be less than left number in a range expression."));
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
        if (!(ctx.mapExpr().expr(0) instanceof TestLangParser.RangeExprContext)) {
            interpreterResponses.add(new InterpreterResponse(ResponseStatus.ERROR, "First argument must be a range expression: {expr1, expr2}"));
            return null;
        }
        Object range = visit(ctx.mapExpr().expr(0)); // Evaluate the range expression

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

    /**
     * Visits a 'reduce' expression in the language, which applies a binary lambda function to elements
     * of a sequence, accumulating the result of the function application.
     *
     * <p>The 'reduce' expression has the following structure:
     * REDUCE '(' expr ',' expr ',' ID ID ARROW expr ')'
     * - The first expr represents the input sequence.
     * - The second expr represents the initial value.
     * - ID ID ARROW expr represents the lambda function that takes two arguments and returns a single value.
     *
     * <p>Example usage in the language:
     * reduce({1,4}, 0, a b -> a + b)
     *
     * @param ctx The ReduceExpressionContext object representing the 'reduce' expression in the parse tree.
     * @return The result of the 'reduce' operation after applying the lambda function on the input sequence.
     */
    @Override
    public Object visitReduceExpression(TestLangParser.ReduceExpressionContext ctx) {
        Object range = visit(ctx.reduceExpr().expr(0)); // Evaluate the range expression

        if (!(range instanceof List)) {
            interpreterResponses.add(new InterpreterResponse(ResponseStatus.ERROR, "Reduce function expects a sequence as the first argument."));
            return null;
        }

        List<Object> sequence = (List<Object>) range;

        // Get the initial value
        Object initialValue = visit(ctx.reduceExpr().expr(1));

        // Create a new scope for the lambda function
        context.enterScope();

        String lambdaVarName1 = ctx.reduceExpr().ID(0).getText();
        String lambdaVarName2 = ctx.reduceExpr().ID(1).getText();

        BinaryOperator<Object> lambdaFunction = (input1, input2) -> {
            context.setVariable(lambdaVarName1, input1);
            context.setVariable(lambdaVarName2, input2);
            return visit(ctx.reduceExpr().expr(2));
        };

        Object reducedValue = sequence.stream().reduce(initialValue, lambdaFunction);

        // Exit the lambda function scope
        context.exitScope();

        return reducedValue;
    }

}
