package org.example.interpreter;

import org.example.ast.*;
import org.example.editor.InterpreterResponse;
import org.example.editor.ResponseStatus;

import java.util.ArrayList;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AstEvaluator {
    private final EvaluationContext context;
    private final ArrayList<InterpreterResponse> interpreterResponses;

    public EvaluationContext getEvaluationContext() {
        return context;
    }

    public AstEvaluator(ArrayList<InterpreterResponse> interpreterResponses) {
        this.context = new EvaluationContext();
        this.interpreterResponses = interpreterResponses;
    }

    public void evaluateProgram(ProgramNode program) {
        for (StmtNode stmt : program.getStatements()) {
            evaluateStmt(stmt);
        }
    }

    private void evaluateStmt(StmtNode stmt) {
        if (stmt instanceof VarDeclNode) {
            evaluateVarDecl((VarDeclNode) stmt);
        } else if (stmt instanceof OutExprNode) {
            evaluateOutExpr((OutExprNode) stmt);
        } else if (stmt instanceof PrintStringNode) {
            evaluatePrintString((PrintStringNode) stmt);
        }
    }

    private void evaluateVarDecl(VarDeclNode varDecl) {
        Object value = evaluateExpr(varDecl.getExpression());
        String variableName = varDecl.getIdentifier();
        if (variableName != null && value != null) {
            context.setVariable(variableName, value);
        }
    }

    /**
     * Evaluates an OutExprNode and adds the result to the interpreterResponses list. If the result is
     * an ArrayList with more than 1000 elements, only the first 500 elements, three dots, and the last
     * 5 elements are included in the response.
     *
     * @param outExpr The OutExprNode to be evaluated.
     */
    private void evaluateOutExpr(OutExprNode outExpr) {
        Object value = evaluateExpr(outExpr.getExpression());

        if (value != null) {
            if (value instanceof Stream || value instanceof IntStream) {
                Stream<?> stream = null;
                if (value instanceof Stream) {
                    stream = (Stream<?>) value;
                } else {
                    stream = ((IntStream) value).boxed();
                }

                String streamAsString = stream
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", "[", "]"));

                interpreterResponses.add(new InterpreterResponse(ResponseStatus.SUCCESS, streamAsString));

            } else {
                interpreterResponses.add(new InterpreterResponse(ResponseStatus.SUCCESS, value.toString()));
            }
        }
    }


    private void evaluatePrintString(PrintStringNode printString) {
        String str = printString.getStringValue();
        if (!str.isEmpty()) {
            interpreterResponses.add(new InterpreterResponse(ResponseStatus.SUCCESS, str));
        }
    }

    private Object evaluateExpr(ExprNode expr) {
        if (expr instanceof IdentifierNode) {
            return evaluateIdentifier((IdentifierNode) expr);
        } else if (expr instanceof NumberLiteralNode) {
            return evaluateNumberLiteral((NumberLiteralNode) expr);
        } else if (expr instanceof RangeExprNode) {
            return evaluateRangeExpr((RangeExprNode) expr);
        } else if (expr instanceof ParenExprNode) {
            return evaluateParenExpr((ParenExprNode) expr);
        } else if (expr instanceof AddNode) {
            return evaluateAddNode((AddNode) expr);
        } else if (expr instanceof SubNode) {
            return evaluateSubNode((SubNode) expr);
        } else if (expr instanceof MulNode) {
            return evaluateMulNode((MulNode) expr);
        } else if (expr instanceof DivNode) {
            return evaluateDivNode((DivNode) expr);
        } else if (expr instanceof PowNode) {
            return evaluatePowNode((PowNode) expr);
        } else if (expr instanceof MapExprNode) {
            return evaluateMapExpr((MapExprNode) expr);
        } else if (expr instanceof ReduceExprNode) {
            return evaluateReduceExpr((ReduceExprNode) expr);
        }
        return null;
    }

    private Object evaluateIdentifier(IdentifierNode identifier) {
        String variableName = identifier.getName();
        if (!context.isVariableDeclared(variableName)) {
            throw new InterpreterException("Variable '" + variableName + "' is not declared.");
        }
        return context.getVariable(variableName);
    }

    private Object evaluateNumberLiteral(NumberLiteralNode numberLiteral) {
        return numberLiteral.getValue();
    }


    private IntStream evaluateRangeExpr(RangeExprNode rangeExpr) {
        Object left = evaluateExpr(rangeExpr.getExpr1());
        Object right = evaluateExpr(rangeExpr.getExpr2());

        if (!(left instanceof Integer leftNumber) || !(right instanceof Integer rightNumber)) {
            throw new InterpreterException("Only integers are allowed in range expressions.");
        }

        if (rightNumber < leftNumber) {
            throw new InterpreterException("Right number must not be less than left number in a range expression.");
        }

        return IntStream.rangeClosed(leftNumber, rightNumber);
    }


    private Object evaluateParenExpr(ParenExprNode parenExpr) {
        return evaluateExpr(parenExpr.getInnerExpr());
    }

    private Object evaluateAddNode(AddNode addNode) {
        Object left = evaluateExpr(addNode.getLeft());
        Object right = evaluateExpr(addNode.getRight());

        if (!(left instanceof Number leftNumber) || !(right instanceof Number rightNumber)) {
            throw new InterpreterException("Invalid operands for addition, operands should be numbers");
        }

        return leftNumber.doubleValue() + rightNumber.doubleValue();
    }


    private Object evaluateSubNode(SubNode subNode) {
        Object left = evaluateExpr(subNode.getLeft());
        Object right = evaluateExpr(subNode.getRight());

        if (!(left instanceof Number leftNumber) || !(right instanceof Number rightNumber)) {
            throw new InterpreterException("Invalid operands for subtraction, operands should be numbers");
        }

        return leftNumber.doubleValue() - rightNumber.doubleValue();
    }

    private Object evaluateMulNode(MulNode mulNode) {
        Object left = evaluateExpr(mulNode.getLeft());
        Object right = evaluateExpr(mulNode.getRight());

        if (!(left instanceof Number leftNumber) || !(right instanceof Number rightNumber)) {
            throw new InterpreterException("Invalid operands for multiplication, operands should be numbers");
        }

        return leftNumber.doubleValue() * rightNumber.doubleValue();
    }

    private Object evaluateDivNode(DivNode divNode) {
        Object left = evaluateExpr(divNode.getLeft());
        Object right = evaluateExpr(divNode.getRight());

        if (!(left instanceof Number leftNumber) || !(right instanceof Number rightNumber)) {
            throw new InterpreterException("Invalid operands for division, operands should be numbers");
        }
        if (rightNumber.doubleValue() == 0) {
            throw new RuntimeException("Division by zero is not allowed.");
        }
        return leftNumber.doubleValue() / rightNumber.doubleValue();
    }

    private Object evaluatePowNode(PowNode powNode) {
        Object base = evaluateExpr(powNode.getLeft());
        Object exponent = evaluateExpr(powNode.getRight());

        if (!(base instanceof Number baseNum) || !(exponent instanceof Number exponentNum)) {
            throw new InterpreterException("Operands must be numbers for exponentiation");
        }
        return Math.pow(baseNum.doubleValue(), exponentNum.doubleValue());
    }

    /**
     * Evaluates a MapExprNode instance, which represents a map expression in the AST.
     * This method processes the sequence and lambda function for the map operation, and returns
     * the result of the map operation applied to the sequence.
     *
     * @param mapExpr The MapExprNode object representing the map expression in the AST.
     * @return An Object representing the result of the map operation applied to the sequence.
     */
    private Stream<Object> evaluateMapExpr(MapExprNode mapExpr) {
        IntStream sequence = (IntStream) evaluateExpr(mapExpr.getSequence());
        context.enterScope();
        String lambdaVarName = mapExpr.getIdentifier();

        IntFunction<Object> lambdaFunction = (input) -> {
            context.setVariable(lambdaVarName, input);
            return evaluateExpr(mapExpr.getBody());
        };

        Stream<Object> mappedSequence = sequence.mapToObj(lambdaFunction);
        context.exitScope();
        return mappedSequence;
    }


    /**
     * Evaluates a ReduceExprNode instance, which represents a 'reduce' expression in the AST.
     * This method processes the range expression, initial value, and lambda function for the reduce operation,
     * and returns the result of the reduce operation.
     *
     * @param reduceExpr The ReduceExprNode object representing the 'reduce' expression in the AST.
     * @return An Object representing the result of the reduce operation.
     * @throws InterpreterException If the reduce function does not receive a sequence as the first argument.
     */
    private Object evaluateReduceExpr(ReduceExprNode reduceExpr) {
        Object range = evaluateExpr(reduceExpr.getSequence());

        if (!(range instanceof IntStream) && !(range instanceof Stream)) {
            throw new InterpreterException("Reduce function expects a sequence of IntStream or Stream<Number> as the first argument.");
        }

        Stream<Number> numberStream;
        if (range instanceof IntStream sequence) {
            numberStream = sequence.mapToObj(Integer::valueOf);
        } else {
            numberStream = (Stream<Number>) range;
        }

        Number initialValue = (Number) evaluateExpr(reduceExpr.getNeutralElement());
        context.enterScope();

        String lambdaVarName1 = reduceExpr.getIdentifier1();
        String lambdaVarName2 = reduceExpr.getIdentifier2();

        // Use a BinaryOperator<Number> to combine the numbers
        BinaryOperator<Number> numberCombiner = (number1, number2) -> {
            context.setVariable(lambdaVarName1, number1);
            context.setVariable(lambdaVarName2, number2);
            return (Number) evaluateExpr(reduceExpr.getBody());
        };

        Number reducedValue = numberStream.reduce(initialValue, numberCombiner);
        context.exitScope();
        return reducedValue;
    }


}
