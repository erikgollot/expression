grammar ExpressionsGrammar;

MULT  : '*' ;
DIV   : '/' ;
PLUS  : '+' ;
MINUS : '-' ;
 
LPAREN : '(' ;
RPAREN : ')' ;

DECIMAL : '-'?[0-9]+('.'[0-9]+)? ;
 
IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]* ;
 
SEMI : ';' ;

COMMENT : '//' .+? ('\n'|EOF) -> skip ;

WS : [ \r\t\u000C\n]+ -> skip ;

arithmetic_expr
 : arithmetic_expr MULT arithmetic_expr  # ArithmeticExpressionMult
 | arithmetic_expr DIV arithmetic_expr   # ArithmeticExpressionDiv
 | arithmetic_expr PLUS arithmetic_expr  # ArithmeticExpressionPlus
 | arithmetic_expr MINUS arithmetic_expr # ArithmeticExpressionMinus
 | MINUS arithmetic_expr                 # ArithmeticExpressionNegation
 | LPAREN arithmetic_expr RPAREN         # ArithmeticExpressionParens
 | numeric_entity                        # ArithmeticExpressionNumericEntity
 ;

numeric_entity : DECIMAL              # NumericConst
               | IDENTIFIER           # NumericVariable
               ;