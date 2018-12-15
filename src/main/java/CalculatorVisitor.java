import org.antlr.v4.runtime.tree.TerminalNode;

public class CalculatorVisitor extends CalculatorGrammerBaseVisitor<Integer> {

    @Override
    public Integer visitAddExp(CalculatorGrammerParser.AddExpContext ctx) {
      //  return super.visitAddExp(ctx);
        int left = visit(ctx.NUMBER(0));
        int right = visit(ctx.NUMBER(1));

        return left + right;
    }

    @Override
    public Integer visitTerminal(TerminalNode node) {
        return Integer.parseInt(node.getText());
    }
}
