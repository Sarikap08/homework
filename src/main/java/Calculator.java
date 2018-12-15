import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;

public class Calculator {

    static final Logger LOGGER = Logger.getLogger(Calculator.class);
    public static void main (String args[]){
        try {
            ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));

            CalculatorGrammerLexer lexer = new CalculatorGrammerLexer(input);
            CalculatorGrammerParser parser = new CalculatorGrammerParser(new CommonTokenStream(lexer));

            CalculatorVisitor visitor = new CalculatorVisitor();
            Integer result = visitor.visit(parser.prog());
            LOGGER.info("Result :"+result);
        } catch (IOException e) {
            LOGGER.error("Error while parsing the expression" + e.getMessage());
        }
    }
}
