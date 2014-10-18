package expressions;

/**
 * Created by maximiliano on 11/10/14.
 */
public class ExponentiationExpression implements IExpression {
    private IExpression expression;
    private IExpression exponent;

    public ExponentiationExpression(IExpression anExpression, IExpression anExponent) {
        expression = anExpression;
        exponent = anExponent;
    }

    @Override
    public Double evaluate() throws InvalidEvaluationException {
        return Math.pow(expression.evaluate(), exponent.evaluate());
    }

    @Override
    public Double subtractFrom(IExpression leftExpression) throws InvalidEvaluationException {
        return leftExpression.evaluate() - evaluate();
    }

    @Override
    public Double divideFrom(IExpression leftExpression) throws InvalidEvaluationException {
        return leftExpression.evaluate() / evaluate();
    }
}
