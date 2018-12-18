grammar CalculatorGrammer;

prog : expression ;

expression : ADD OB expression COMMA expression CB #addExp
            | LET OB VAR COMMA expression COMMA expression CB #letExp
            | NUMBER #numExp
            | VAR #varExp
            | MULT OB expression COMMA expression CB #multExp
            | DIV OB expression COMMA expression CB #divExp
            | SUB OB expression COMMA expression CB #subExp ;

ADD : 'add' ;
SUB : 'sub' ;
DIV : 'div' ;
MULT : 'mult' ;
LET : 'let';
COMMA : ',';
OB    : '(';
CB    : ')';

VAR : [a-z]+ ;
NUMBER  : '-'?[0-9]+ ;
WS     : [ \n\t]+ -> skip;
NEWLINE:'\r'? '\n' ;

