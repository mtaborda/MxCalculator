package models;

import expressions.InvalidCharacterForAMathExpressionException;
import expressions.InvalidEvaluationException;
import expressions.NotBalancedExpressionException;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator = new Calculator();
    private Double result;

    @Test
    public void testAddition() throws InvalidEvaluationException,
            InvalidCharacterForAMathExpressionException, NotBalancedExpressionException {

        result = calculator.calculate("2+3");
        assertEquals(Double.valueOf(5), result);

        result = calculator.calculate("2+3+4");
        assertEquals(Double.valueOf(9), result);

        result = calculator.calculate("2+3+4+5");
        assertEquals(Double.valueOf(14), result);

        result = calculator.calculate("2.5+3");
        assertEquals(Double.valueOf(5.5), result);

        result = calculator.calculate("2+3.2+4+5.1");
        assertEquals(Double.valueOf(14.3), result);

        result = calculator.calculate("-2+3");
        assertEquals(Double.valueOf(1), result);

        result = calculator.calculate("2.5+-3");
        assertEquals(Double.valueOf(-0.5), result);
    }

    @Test
    public void testSubtraction() throws InvalidEvaluationException,
            InvalidCharacterForAMathExpressionException, NotBalancedExpressionException {

        result = calculator.calculate("3-2");
        assertEquals(Double.valueOf(1), result);

        result = calculator.calculate("-2-3");
        assertEquals(Double.valueOf(-5), result);

        result = calculator.calculate("2-3");
        assertEquals(Double.valueOf(-1), result);

        result = calculator.calculate("2.5-3");
        assertEquals(Double.valueOf(-0.5), result);

        result = calculator.calculate("4-2-1");
        assertEquals(Double.valueOf(1), result);

        result = calculator.calculate("-1-2-3");
        assertEquals(Double.valueOf(-6), result);

        result = calculator.calculate("2-3--5");
        assertEquals(Double.valueOf(4), result);
    }

    @Test
    public void testMultiplication() throws InvalidEvaluationException,
            InvalidCharacterForAMathExpressionException, NotBalancedExpressionException {

        result = calculator.calculate("3*2");
        assertEquals(Double.valueOf(6), result);

        result = calculator.calculate("-2*3");
        assertEquals(Double.valueOf(-6), result);

        result = calculator.calculate("2*-3");
        assertEquals(Double.valueOf(-6), result);

        result = calculator.calculate("2.5*3");
        assertEquals(Double.valueOf(7.5), result);

        result = calculator.calculate("4*2*1");
        assertEquals(Double.valueOf(8), result);

        result = calculator.calculate("-1*2*3");
        assertEquals(Double.valueOf(-6), result);

        result = calculator.calculate("2*3*-5");
        assertEquals(Double.valueOf(-30), result);
    }

    @Test
    public void testDivision() throws InvalidEvaluationException,
            InvalidCharacterForAMathExpressionException, NotBalancedExpressionException {

        DecimalFormat formatter = new DecimalFormat("####0.00");

        result = calculator.calculate("3/2");
        assertEquals(Double.valueOf(1.5), result);

        result = calculator.calculate("-2/3");
        assertEquals("-0,67", formatter.format(result));

        result = calculator.calculate("2/-3");
        assertEquals("-0,67", formatter.format(result));

        result = calculator.calculate("2.5/3");
        assertEquals("0,83", formatter.format(result));

        result = calculator.calculate("4/2/1");
        assertEquals(Double.valueOf(2), result);

        result = calculator.calculate("-1/2/3");
        assertEquals("-0,17", formatter.format(result));

        result = calculator.calculate("2/3/-5");
        assertEquals("-0,13", formatter.format(result));
    }

    @Test
    public void testLogarithm() throws InvalidEvaluationException,
            InvalidCharacterForAMathExpressionException, NotBalancedExpressionException {

        DecimalFormat formatter = new DecimalFormat("####0.00");

        result = calculator.calculate("log 3");
        assertEquals("0,48", formatter.format(result));

        try {
            result = calculator.calculate("log -3");
        } catch (InvalidEvaluationException ex) {
            assertEquals("Invalid evaluation <log -3.0>", ex.getMessage());
        }

        result = calculator.calculate("log 3.2");
        assertEquals("0,51", formatter.format(result));

        result = calculator.calculate("log 1");
        assertEquals(Double.valueOf(0), result);

        try {
            result = calculator.calculate("log -1");
        } catch (InvalidEvaluationException ex) {
            assertEquals("Invalid evaluation <log -1.0>", ex.getMessage());
        }

        result = calculator.calculate("log 0");
        assertTrue(Double.NEGATIVE_INFINITY == result);

        try {
            result = calculator.calculate("log -4.5");
        } catch (InvalidEvaluationException ex) {
            assertEquals("Invalid evaluation <log -4.5>", ex.getMessage());
        }
    }

    @Test
    public void testExponentiation() throws InvalidEvaluationException,
            InvalidCharacterForAMathExpressionException, NotBalancedExpressionException {

        result = calculator.calculate("3^2");
        assertEquals(Double.valueOf(9), result);

        result = calculator.calculate("-2^3");
        assertEquals(Double.valueOf(-8), result);

        result = calculator.calculate("2^-3");
        assertEquals(Double.valueOf(0.125), result);

        result = calculator.calculate("2.5^3");
        assertEquals(Double.valueOf(15.625), result);

        result = calculator.calculate("4^2");
        assertEquals(Double.valueOf(16), result);

        result = calculator.calculate("-1^2");
        assertEquals(Double.valueOf(1), result);

        result = calculator.calculate("2^0");
        assertEquals(Double.valueOf(1), result);
    }

    @Test
    public void testComposite() throws InvalidEvaluationException,
            InvalidCharacterForAMathExpressionException, NotBalancedExpressionException {

        DecimalFormat formatter = new DecimalFormat("####0.00");

        result = calculator.calculate("(2+1)");
        assertEquals(Double.valueOf(3), result);

        result = calculator.calculate("(((2)+(1)))");
        assertEquals(Double.valueOf(3), result);

        result = calculator.calculate("(2*2)-4");
        assertEquals(Double.valueOf(0), result);

        result = calculator.calculate("5*3*(8-23)");
        assertEquals(Double.valueOf(-225), result);

        result = calculator.calculate("(2+2)*(log 10)/3");
        assertEquals("1,33", formatter.format(result));

        result = calculator.calculate("(2+2)*log (10/3)");
        assertEquals("2,09", formatter.format(result));

        result = calculator.calculate("(2+2)*log 10/3");
        assertEquals("2,09", formatter.format(result));
    }
}