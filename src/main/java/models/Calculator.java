package models;

import expressions.*;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by maximiliano on 11/10/14.
 */
public class Calculator {
    public Double calculate(String anExpression) throws InvalidEvaluationException,
            InvalidCharacterForAMathExpressionException, NotBalancedExpressionException {

        ExpressionParser parser = new ExpressionParser();
        if (StringUtils.countMatches(anExpression, "(")!=StringUtils.countMatches(anExpression,")")) {
            throw new NotBalancedExpressionException(anExpression);
        }
        IExpression expression = parser.parse(anExpression);

        return expression.evaluate();
    }

    public Calculation calculateExpression(String anExpression) {

        String result;
        try {
            result = calculate(anExpression).toString();
        } catch (InvalidEvaluationException e) {
            //e.printStackTrace();
            result = e.getMessage();
        } catch (InvalidCharacterForAMathExpressionException e) {
            //e.printStackTrace();
            result = e.getMessage();
        } catch (NotBalancedExpressionException e) {
            //e.printStackTrace();
            result = e.getMessage();
        }

        return new Calculation(anExpression, result);
    }
}
