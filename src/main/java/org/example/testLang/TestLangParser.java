package org.example.testLang;// Generated from TestLang.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.example.interpreter.MyCustomVisitor;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class TestLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, VAR=12, OUT=13, PRINT=14, MAP=15, REDUCE=16, ARROW=17, 
		ID=18, NUMBER=19, STRING=20, WS=21;
	public static final int
		RULE_program = 0, RULE_stmt = 1, RULE_expr = 2, RULE_mapExpr = 3, RULE_reduceExpr = 4, 
		RULE_op = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stmt", "expr", "mapExpr", "reduceExpr", "op"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'+'", "'-'", "'*'", "'/'", "'^'", "'('", "')'", "'{'", 
			"','", "'}'", "'var'", "'out'", "'print'", "'map'", "'reduce'", "'->'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"VAR", "OUT", "PRINT", "MAP", "REDUCE", "ARROW", "ID", "NUMBER", "STRING", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TestLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TestLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TestLangParser.EOF, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 28672L) != 0)) {
				{
				{
				setState(12);
				stmt();
				}
				}
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(18);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintStringContext extends StmtContext {
		public TerminalNode PRINT() { return getToken(TestLangParser.PRINT, 0); }
		public TerminalNode STRING() { return getToken(TestLangParser.STRING, 0); }
		public PrintStringContext(StmtContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OutExprContext extends StmtContext {
		public TerminalNode OUT() { return getToken(TestLangParser.OUT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public OutExprContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyCustomVisitor) return (T) ((MyCustomVisitor)visitor).visitOutExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclContext extends StmtContext {
		public TerminalNode VAR() { return getToken(TestLangParser.VAR, 0); }
		public TerminalNode ID() { return getToken(TestLangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarDeclContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyCustomVisitor) return (T) ((MyCustomVisitor)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmt);
		try {
			setState(28);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				_localctx = new VarDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				match(VAR);
				setState(21);
				match(ID);
				setState(22);
				match(T__0);
				setState(23);
				expr(0);
				}
				break;
			case OUT:
				_localctx = new OutExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(24);
				match(OUT);
				setState(25);
				expr(0);
				}
				break;
			case PRINT:
				_localctx = new PrintStringContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(26);
				match(PRINT);
				setState(27);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MapExpressionContext extends ExprContext {
		public MapExprContext mapExpr() {
			return getRuleContext(MapExprContext.class,0);
		}
		public MapExpressionContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ExprContext {
		public TerminalNode ID() { return getToken(TestLangParser.ID, 0); }
		public IdentifierContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyCustomVisitor) return (T) ((MyCustomVisitor)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivExprContext extends ExprContext {
		public Token mulDivOp;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MulDivExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReduceExpressionContext extends ExprContext {
		public ReduceExprContext reduceExpr() {
			return getRuleContext(ReduceExprContext.class,0);
		}
		public ReduceExpressionContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PowExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PowExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RangeExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public RangeExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubExprContext extends ExprContext {
		public Token addSubOp;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddSubExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberLiteralContext extends ExprContext {
		public TerminalNode NUMBER() { return getToken(TestLangParser.NUMBER, 0); }
		public NumberLiteralContext(ExprContext ctx) { copyFrom(ctx); }

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyCustomVisitor) return (T) ((MyCustomVisitor)visitor).visitNumberLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(31);
				match(T__6);
				setState(32);
				expr(0);
				setState(33);
				match(T__7);
				}
				break;
			case ID:
				{
				_localctx = new IdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(35);
				match(ID);
				}
				break;
			case T__8:
				{
				_localctx = new RangeExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(36);
				match(T__8);
				setState(37);
				expr(0);
				setState(38);
				match(T__9);
				setState(39);
				expr(0);
				setState(40);
				match(T__10);
				}
				break;
			case NUMBER:
				{
				_localctx = new NumberLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(42);
				match(NUMBER);
				}
				break;
			case MAP:
				{
				_localctx = new MapExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(43);
				mapExpr();
				}
				break;
			case REDUCE:
				{
				_localctx = new ReduceExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(44);
				reduceExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(58);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(56);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new AddSubExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(47);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(48);
						((AddSubExprContext)_localctx).addSubOp = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__1 || _la==T__2) ) {
							((AddSubExprContext)_localctx).addSubOp = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(49);
						expr(10);
						}
						break;
					case 2:
						{
						_localctx = new MulDivExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(50);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(51);
						((MulDivExprContext)_localctx).mulDivOp = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__3 || _la==T__4) ) {
							((MulDivExprContext)_localctx).mulDivOp = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(52);
						expr(9);
						}
						break;
					case 3:
						{
						_localctx = new PowExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(53);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(54);
						match(T__5);
						setState(55);
						expr(8);
						}
						break;
					}
					} 
				}
				setState(60);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MapExprContext extends ParserRuleContext {
		public TerminalNode MAP() { return getToken(TestLangParser.MAP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ID() { return getToken(TestLangParser.ID, 0); }
		public TerminalNode ARROW() { return getToken(TestLangParser.ARROW, 0); }
		public MapExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapExpr; }
	}

	public final MapExprContext mapExpr() throws RecognitionException {
		MapExprContext _localctx = new MapExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_mapExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(MAP);
			setState(62);
			match(T__6);
			setState(63);
			expr(0);
			setState(64);
			match(T__9);
			setState(65);
			match(ID);
			setState(66);
			match(ARROW);
			setState(67);
			expr(0);
			setState(68);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReduceExprContext extends ParserRuleContext {
		public TerminalNode REDUCE() { return getToken(TestLangParser.REDUCE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(TestLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(TestLangParser.ID, i);
		}
		public TerminalNode ARROW() { return getToken(TestLangParser.ARROW, 0); }
		public ReduceExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reduceExpr; }
	}

	public final ReduceExprContext reduceExpr() throws RecognitionException {
		ReduceExprContext _localctx = new ReduceExprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_reduceExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(REDUCE);
			setState(71);
			match(T__6);
			setState(72);
			expr(0);
			setState(73);
			match(T__9);
			setState(74);
			expr(0);
			setState(75);
			match(T__9);
			setState(76);
			match(ID);
			setState(77);
			match(ID);
			setState(78);
			match(ARROW);
			setState(79);
			expr(0);
			setState(80);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OpContext extends ParserRuleContext {
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 124L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0015U\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0001\u0000\u0005\u0000\u000e\b\u0000\n\u0000\f\u0000"+
		"\u0011\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"\u001d\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002.\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u00029\b\u0002\n\u0002\f\u0002"+
		"<\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0000\u0001\u0004\u0006\u0000\u0002\u0004\u0006\b\n\u0000"+
		"\u0003\u0001\u0000\u0002\u0003\u0001\u0000\u0004\u0005\u0001\u0000\u0002"+
		"\u0006Y\u0000\u000f\u0001\u0000\u0000\u0000\u0002\u001c\u0001\u0000\u0000"+
		"\u0000\u0004-\u0001\u0000\u0000\u0000\u0006=\u0001\u0000\u0000\u0000\b"+
		"F\u0001\u0000\u0000\u0000\nR\u0001\u0000\u0000\u0000\f\u000e\u0003\u0002"+
		"\u0001\u0000\r\f\u0001\u0000\u0000\u0000\u000e\u0011\u0001\u0000\u0000"+
		"\u0000\u000f\r\u0001\u0000\u0000\u0000\u000f\u0010\u0001\u0000\u0000\u0000"+
		"\u0010\u0012\u0001\u0000\u0000\u0000\u0011\u000f\u0001\u0000\u0000\u0000"+
		"\u0012\u0013\u0005\u0000\u0000\u0001\u0013\u0001\u0001\u0000\u0000\u0000"+
		"\u0014\u0015\u0005\f\u0000\u0000\u0015\u0016\u0005\u0012\u0000\u0000\u0016"+
		"\u0017\u0005\u0001\u0000\u0000\u0017\u001d\u0003\u0004\u0002\u0000\u0018"+
		"\u0019\u0005\r\u0000\u0000\u0019\u001d\u0003\u0004\u0002\u0000\u001a\u001b"+
		"\u0005\u000e\u0000\u0000\u001b\u001d\u0005\u0014\u0000\u0000\u001c\u0014"+
		"\u0001\u0000\u0000\u0000\u001c\u0018\u0001\u0000\u0000\u0000\u001c\u001a"+
		"\u0001\u0000\u0000\u0000\u001d\u0003\u0001\u0000\u0000\u0000\u001e\u001f"+
		"\u0006\u0002\uffff\uffff\u0000\u001f \u0005\u0007\u0000\u0000 !\u0003"+
		"\u0004\u0002\u0000!\"\u0005\b\u0000\u0000\".\u0001\u0000\u0000\u0000#"+
		".\u0005\u0012\u0000\u0000$%\u0005\t\u0000\u0000%&\u0003\u0004\u0002\u0000"+
		"&\'\u0005\n\u0000\u0000\'(\u0003\u0004\u0002\u0000()\u0005\u000b\u0000"+
		"\u0000).\u0001\u0000\u0000\u0000*.\u0005\u0013\u0000\u0000+.\u0003\u0006"+
		"\u0003\u0000,.\u0003\b\u0004\u0000-\u001e\u0001\u0000\u0000\u0000-#\u0001"+
		"\u0000\u0000\u0000-$\u0001\u0000\u0000\u0000-*\u0001\u0000\u0000\u0000"+
		"-+\u0001\u0000\u0000\u0000-,\u0001\u0000\u0000\u0000.:\u0001\u0000\u0000"+
		"\u0000/0\n\t\u0000\u000001\u0007\u0000\u0000\u000019\u0003\u0004\u0002"+
		"\n23\n\b\u0000\u000034\u0007\u0001\u0000\u000049\u0003\u0004\u0002\t5"+
		"6\n\u0007\u0000\u000067\u0005\u0006\u0000\u000079\u0003\u0004\u0002\b"+
		"8/\u0001\u0000\u0000\u000082\u0001\u0000\u0000\u000085\u0001\u0000\u0000"+
		"\u00009<\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000:;\u0001\u0000"+
		"\u0000\u0000;\u0005\u0001\u0000\u0000\u0000<:\u0001\u0000\u0000\u0000"+
		"=>\u0005\u000f\u0000\u0000>?\u0005\u0007\u0000\u0000?@\u0003\u0004\u0002"+
		"\u0000@A\u0005\n\u0000\u0000AB\u0005\u0012\u0000\u0000BC\u0005\u0011\u0000"+
		"\u0000CD\u0003\u0004\u0002\u0000DE\u0005\b\u0000\u0000E\u0007\u0001\u0000"+
		"\u0000\u0000FG\u0005\u0010\u0000\u0000GH\u0005\u0007\u0000\u0000HI\u0003"+
		"\u0004\u0002\u0000IJ\u0005\n\u0000\u0000JK\u0003\u0004\u0002\u0000KL\u0005"+
		"\n\u0000\u0000LM\u0005\u0012\u0000\u0000MN\u0005\u0012\u0000\u0000NO\u0005"+
		"\u0011\u0000\u0000OP\u0003\u0004\u0002\u0000PQ\u0005\b\u0000\u0000Q\t"+
		"\u0001\u0000\u0000\u0000RS\u0007\u0002\u0000\u0000S\u000b\u0001\u0000"+
		"\u0000\u0000\u0005\u000f\u001c-8:";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
