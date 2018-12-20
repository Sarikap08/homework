package com.synopsys.calculator;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class CalculatorVisitor extends CalculatorGrammerBaseVisitor<Integer> {

    static final Logger LOGGER = Logger.getLogger(CalculatorVisitor.class);
    private Map<String,Integer> map = new HashMap<String,Integer>();

    @Override
    public Integer visitDivExp(com.synopsys.calculator.CalculatorGrammerParser.DivExpContext ctx) {
        Integer leftValue = visit(ctx.expression(0));
        Integer rightValue = visit(ctx.expression(1));
        LOGGER.debug("visitDivExp leftValue -> "+leftValue +" AND rightValue -> "+rightValue);

        Integer result = null;
        try {
            result =floorDiv(leftValue, rightValue);
        }catch(NullPointerException e1){
            throwsNullPointerException(leftValue, rightValue);
        }
        catch(ArithmeticException e2){
            throwsArithmeticException(leftValue, rightValue, e2);
        }
        return result;
    }

    @Override
    public Integer visitMultExp(com.synopsys.calculator.CalculatorGrammerParser.MultExpContext ctx) {
        Integer leftValue = visit(ctx.expression(0));
        Integer rightValue = visit(ctx.expression(1));
        LOGGER.debug("visitMultExp leftValue -> "+leftValue +" AND rightValue -> "+rightValue);

        Integer result = null;
        try {
            result =multiplyExact(leftValue,rightValue);
        }catch(NullPointerException e1){
            throwsNullPointerException(leftValue, rightValue);
        }
        catch(ArithmeticException e2){
            throwsArithmeticException(leftValue, rightValue, e2);
        }
        return result;
    }

    @Override
    public Integer visitSubExp(com.synopsys.calculator.CalculatorGrammerParser.SubExpContext ctx) {
        Integer leftValue = visit(ctx.expression(0));
        Integer rightValue = visit(ctx.expression(1));
        LOGGER.debug("visitSubExp leftValue -> "+leftValue +" AND rightValue -> "+rightValue);

        Integer result = null;
        try {
            result =subtractExact(leftValue ,rightValue);
        }catch(NullPointerException e1){
            throwsNullPointerException(leftValue, rightValue);
        }
        catch(ArithmeticException e2){
            throwsArithmeticException(leftValue, rightValue, e2);
        }
        return result;
    }

    @Override
    public Integer visitAddExp(com.synopsys.calculator.CalculatorGrammerParser.AddExpContext ctx) {
        Integer leftValue = visit(ctx.expression(0));
        Integer rightValue = visit(ctx.expression(1));
        LOGGER.debug("visitAddExp leftValue -> "+leftValue +" AND rightValue -> "+rightValue);

        Integer result = null;
        try {
            result =addExact(leftValue, rightValue);
        }catch(NullPointerException e1){
            return throwsNullPointerException(leftValue, rightValue);
        }
        catch(ArithmeticException e2){
            return throwsArithmeticException(leftValue, rightValue, e2);
        }
        return result;
    }

    private Integer throwsArithmeticException(Integer leftValue, Integer rightValue, ArithmeticException e2) {
        LOGGER.info(" leftValue -> " + leftValue + " AND rightValue -> " + rightValue);
        throw new ArithmeticException(e2.getMessage());
    }

    private Integer throwsNullPointerException(Integer leftValue, Integer rightValue) {
        LOGGER.info("leftValue -> " + leftValue + " AND rightValue -> " + rightValue);
        throw new NullPointerException("Both Values cannot be null");
    }

    @Override
    public Integer visitLetExp(com.synopsys.calculator.CalculatorGrammerParser.LetExpContext ctx) {
        String var =ctx.VAR().getText();
        Integer value = visit(ctx.expression(0));
        map.put(var,value);
        LOGGER.debug("visitLetExp Variable -> "+var +" AND Value -> "+value);
        Integer result = visit(ctx.expression(1));
        return result;
    }

    @Override
    public Integer visitNumExp(com.synopsys.calculator.CalculatorGrammerParser.NumExpContext ctx) {
        Integer value = Integer.parseInt(ctx.getText());
        return value;
    }

    @Override
    public Integer visitVarExp(com.synopsys.calculator.CalculatorGrammerParser.VarExpContext ctx) {
        return map.get(ctx.getText());
    }
}
