package org.example.testLang;// Generated from TestLang.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TestLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TestLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TestLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(TestLangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDecl}
	 * labeled alternative in {@link TestLangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(TestLangParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OutExpr}
	 * labeled alternative in {@link TestLangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutExpr(TestLangParser.OutExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintString}
	 * labeled alternative in {@link TestLangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintString(TestLangParser.PrintStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MapExpression}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapExpression(TestLangParser.MapExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(TestLangParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpr(TestLangParser.MulDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReduceExpression}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceExpression(TestLangParser.ReduceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PowExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpr(TestLangParser.PowExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(TestLangParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RangeExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeExpr(TestLangParser.RangeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(TestLangParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link TestLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteral(TestLangParser.NumberLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestLangParser#mapExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapExpr(TestLangParser.MapExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestLangParser#reduceExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceExpr(TestLangParser.ReduceExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestLangParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(TestLangParser.OpContext ctx);
}