package org.example.testLang;// Generated from TestLang.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestLangParser}.
 */
public interface TestLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TestLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TestLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarDecl}
	 * labeled alternative in {@link TestLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(TestLangParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarDecl}
	 * labeled alternative in {@link TestLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(TestLangParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OutExpr}
	 * labeled alternative in {@link TestLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterOutExpr(TestLangParser.OutExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OutExpr}
	 * labeled alternative in {@link TestLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitOutExpr(TestLangParser.OutExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintString}
	 * labeled alternative in {@link TestLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintString(TestLangParser.PrintStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintString}
	 * labeled alternative in {@link TestLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintString(TestLangParser.PrintStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MapExpression}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMapExpression(TestLangParser.MapExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MapExpression}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMapExpression(TestLangParser.MapExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(TestLangParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(TestLangParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(TestLangParser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(TestLangParser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReduceExpression}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterReduceExpression(TestLangParser.ReduceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReduceExpression}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitReduceExpression(TestLangParser.ReduceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PowExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPowExpr(TestLangParser.PowExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PowExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPowExpr(TestLangParser.PowExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(TestLangParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(TestLangParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RangeExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRangeExpr(TestLangParser.RangeExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RangeExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRangeExpr(TestLangParser.RangeExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(TestLangParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(TestLangParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(TestLangParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(TestLangParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestLangParser#mapExpr}.
	 * @param ctx the parse tree
	 */
	void enterMapExpr(TestLangParser.MapExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestLangParser#mapExpr}.
	 * @param ctx the parse tree
	 */
	void exitMapExpr(TestLangParser.MapExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestLangParser#reduceExpr}.
	 * @param ctx the parse tree
	 */
	void enterReduceExpr(TestLangParser.ReduceExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestLangParser#reduceExpr}.
	 * @param ctx the parse tree
	 */
	void exitReduceExpr(TestLangParser.ReduceExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestLangParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(TestLangParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestLangParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(TestLangParser.OpContext ctx);
}