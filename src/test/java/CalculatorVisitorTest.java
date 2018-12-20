import com.synopsys.calculator.Calculator;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorVisitorTest {

    @Test
    public void testAddExpression(){
        String positiveValueExpression = "add(110,60)";
        int calculatedValue = Calculator.evaluateExpression(positiveValueExpression);
        Assert.assertEquals(170, calculatedValue);

        String negativeValueExpression = "add(-20,60)";
        calculatedValue = Calculator.evaluateExpression(negativeValueExpression);
        Assert.assertEquals(40, calculatedValue);

    }

    @Test
    public void testSubstractExpression(){
        String positiveValueExpression = "sub(110,60)";
        int calculatedValue = Calculator.evaluateExpression(positiveValueExpression);
        Assert.assertEquals(50, calculatedValue);

        String negativeValueExpression = "sub(-20,-60)";
        calculatedValue = Calculator.evaluateExpression(negativeValueExpression);
        Assert.assertEquals(40, calculatedValue);

    }

    @Test
    public void testMulticationExpression(){
        String positiveValueExpression = "mult(110,60)";
        int calculatedValue = Calculator.evaluateExpression(positiveValueExpression);
        Assert.assertEquals(6600, calculatedValue);

        String negativeValueExpression = "mult(-20,-60)";
        calculatedValue = Calculator.evaluateExpression(negativeValueExpression);
        Assert.assertEquals(1200, calculatedValue);

        String zeroValueExpression = "mult(-20,0)";
        calculatedValue = Calculator.evaluateExpression(zeroValueExpression);
        Assert.assertEquals(0, calculatedValue);

    }

    @Test
    public void testDivisionExpression(){
        String positiveValueExpression = "div(110,60)";
        int calculatedValue = Calculator.evaluateExpression(positiveValueExpression);
        Assert.assertEquals(1, calculatedValue);

        String negativeValueExpression = "div(-60,2)";
        calculatedValue = Calculator.evaluateExpression(negativeValueExpression);
        Assert.assertEquals(-30, calculatedValue);

    }

    @Test(expected = ArithmeticException.class )
    public void testExceptionDivision(){
         String zeroValueExpression = "div(-20,0)";
        int calculatedValue = Calculator.evaluateExpression(zeroValueExpression);
    }

    @Test
    public void testLetExpression(){
        String letSimpleExpression = "let(a,5,add(a,a))";
        int calculatedValue = Calculator.evaluateExpression(letSimpleExpression);
        Assert.assertEquals(10, calculatedValue);

        String letNestedExpression = "let(a,let(b,7,add(b,b)),add(a,b))";
        calculatedValue = Calculator.evaluateExpression(letNestedExpression);
        Assert.assertEquals(21, calculatedValue);

        String letNegativeExpression = "let(a,let(b,-7,add(b,b)),sub(a,b))";
        calculatedValue = Calculator.evaluateExpression(letNegativeExpression);
        Assert.assertEquals(-7, calculatedValue);
    }

    @Test(expected = ParseCancellationException.class )
    public void testExpression(){
        String zeroValueExpression = "div(-20,";
        int calculatedValue = Calculator.evaluateExpression(zeroValueExpression);
        Assert.assertEquals(null, calculatedValue);
    }
}
