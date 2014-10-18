package expressions;

/**
 * Created by maximiliano on 11/10/14.
 */
public class ValueExpression implements IExpression {

    private Double value;

    public ValueExpression(String aValue) {
        value = Double.valueOf(aValue);
    }

    @Override
    public Double evaluate() throws InvalidEvaluationException {
        return value;
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
