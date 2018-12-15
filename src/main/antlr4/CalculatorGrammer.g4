grammar CalculatorGrammer;

prog : expression ;

expression : ADD OB NUMBER COMMA NUMBER CB #addExp ;

//Lexer Rule
ADD : 'add' ;
SUB : 'sub' ;
DIV : 'div' ;
MULT : 'mult' ;
LET : 'let';
COMMA : ',';
OB    : '(';
CB    : ')';

VAR : [a-z]+ ;
NUMBER  : [0-9]+ ;
WS     : [ \n\t]+ -> skip;
NEWLINE:'\r'? '\n' ;

