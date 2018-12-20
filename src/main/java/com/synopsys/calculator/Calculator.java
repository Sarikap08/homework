package com.synopsys.calculator;

import com.synopsys.exception.CalculatorException;
import com.synopsys.exception.DescriptiveErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.apache.log4j.Logger;

/**
 * This is main class which takes expressions to be evaluated
 */
public class Calculator {

    static final Logger LOGGER = Logger.getLogger(Calculator.class);
    public static void main (String args[]) throws CalculatorException{
        if(args.length == 0 ){
            String errorMessage = "Please provide correct input expression ";
            LOGGER.error(errorMessage);
            throw new CalculatorException(errorMessage);
        }

        String expression =  args[0];
        LOGGER.debug("Expression to be evaluated: "+ expression);
        try {
            Integer result = evaluateExpression(expression);
            LOGGER.info("Expression Evaluated Successfully And Result Is -->" + result);
        } catch (RecognitionException e) {
            LOGGER.error("Error while parsing the expression ");
        }catch (NullPointerException ex) {
            String message = "Invalid expression, cannot be evaluated";
            LOGGER.error(message);
        }catch (ArithmeticException ex) {
            String message = "Arithmetic Exception, cannot be evaluated";
            LOGGER.error(ex.getMessage());
        }

    }

    public static Integer evaluateExpression(String expression) {
        Integer calculatedValue = null;

        ANTLRInputStream input = new ANTLRInputStream(expression);

        CalculatorGrammerLexer lexer = new CalculatorGrammerLexer(input);
        CalculatorGrammerParser parser = new CalculatorGrammerParser(new CommonTokenStream(lexer));

        lexer.removeErrorListeners();
        lexer.addErrorListener(DescriptiveErrorListener.INSTANCE);
        parser.removeErrorListeners();
        parser.addErrorListener(DescriptiveErrorListener.INSTANCE);

        CalculatorVisitor visitor = new CalculatorVisitor();
        calculatedValue = visitor.visit(parser.prog());

        return calculatedValue;
    }
}
