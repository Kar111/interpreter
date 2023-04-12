grammar TestLang;

program: stmt* EOF;

stmt: VAR ID '=' expr                # VarDecl
    | OUT expr                       # OutExpr
    | PRINT STRING                   # PrintString
    ;


expr: expr addSubOp=('+' | '-') expr       # AddSubExpr
    | expr mulDivOp=('*' | '/') expr       # MulDivExpr
    | expr '^' expr                        # PowExpr
    | '(' expr ')'                         # ParenExpr
    | ID                                   # Identifier
    | '{' expr ',' expr '}'                # RangeExpr
    | NUMBER                               # NumberLiteral
    | mapExpr                              # MapExpression
    | reduceExpr                           # ReduceExpression
    ;

mapExpr: MAP '(' expr ',' ID ARROW expr ')';

reduceExpr: REDUCE '(' expr ',' expr ',' ID ID ARROW expr ')';


op: '+' | '-' | '*' | '/' | '^';

VAR: 'var';
OUT: 'out';
PRINT: 'print';
MAP: 'map';
REDUCE: 'reduce';
ARROW: '->';

ID: [a-zA-Z_][a-zA-Z_0-9]*;
NUMBER: '-'? [0-9]+ ('.' [0-9]+)?;


STRING: '"' ~["\r\n]* '"';
WS: [ \t\r\n]+ -> skip;

