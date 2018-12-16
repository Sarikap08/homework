import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.Map;

public class CalculatorVisitor extends CalculatorGrammerBaseVisitor<Integer> {

    private Map<String,Integer> map = new HashMap<String,Integer>();

    @Override
    public Integer visitDivExp(CalculatorGrammerParser.DivExpContext ctx) {
        Integer value1 = visit(ctx.expression(0));
        Integer value2 = visit(ctx.expression(1));
        return value1/value2;
    }

    @Override
    public Integer visitMultExp(CalculatorGrammerParser.MultExpContext ctx) {
        Integer value1 = visit(ctx.expression(0));
        Integer value2 = visit(ctx.expression(1));
        return value1*value2;
    }

    @Override
    public Integer visitSubExp(CalculatorGrammerParser.SubExpContext ctx) {
        Integer value1 = visit(ctx.expression(0));
        Integer value2 = visit(ctx.expression(1));
        return value1-value2;
    }

    @Override
    public Integer visitAddExp(CalculatorGrammerParser.AddExpContext ctx) {
        Integer value1 = visit(ctx.expression(0));
        Integer value2 = visit(ctx.expression(1));
        return value1+value2;
    }

    @Override
    public Integer visitTerminal(TerminalNode node) {
        return Integer.parseInt(node.getText());
    }

    @Override
    public Integer visitLetExp(CalculatorGrammerParser.LetExpContext ctx) {
        String var =ctx.VAR().getText();
        Integer value = visit(ctx.expression(0));
        map.put(var,value);
        Integer result = visit(ctx.expression(1));
        return result;
    }

    @Override
    public Integer visitNumExp(CalculatorGrammerParser.NumExpContext ctx) {
        Integer value = Integer.parseInt(ctx.getText());
        return value;
    }

    @Override
    public Integer visitVarExp(CalculatorGrammerParser.VarExpContext ctx) {
        return map.get(ctx.getText());
    }
}
