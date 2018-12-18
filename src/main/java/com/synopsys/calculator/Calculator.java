package com.synopsys.calculator;

import com.synopsys.exception.CalculatorException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.apache.log4j.Logger;

public class Calculator {

    static final Logger LOGGER = Logger.getLogger(Calculator.class);
    public static void main (String args[]) throws CalculatorException{
        if(args.length == 0 ){
            String errorMessage = "Please provide correct input expression ";
            LOGGER.error(errorMessage);
            throw new CalculatorException(errorMessage);
        }

        String expression =  args[0];

        Integer result = evaluateExpression(expression);
        LOGGER.info("Expression Evaluated Successfully And Result Is -->" + result);
    }

    public static Integer evaluateExpression(String expression) {
        Integer calculatedValue = null;
        try {
            ANTLRInputStream input = new ANTLRInputStream(expression);

            CalculatorGrammerLexer lexer = new CalculatorGrammerLexer(input);
            CalculatorGrammerParser parser = new CalculatorGrammerParser(new CommonTokenStream(lexer));

            CalculatorVisitor visitor = new CalculatorVisitor();
            calculatedValue = visitor.visit(parser.prog());
        } catch (RecognitionException e) {
            LOGGER.error("Error while parsing the expression" + e.getMessage());
        }
        return calculatedValue;
    }
}
