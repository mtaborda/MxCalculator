package expressions;

/**
 * Created by maximiliano on 11/10/14.
 */
public class LogarithmExpression implements IExpression {
    private IExpression expression;

    public LogarithmExpression(IExpression anExpression) {
        expression = anExpression;
    }

    @Override
    public Double evaluate() throws InvalidEvaluationException {
        Double right = expression.evaluate();
        if (right<0) {
            throw new InvalidEvaluationException("log " +right.toString());
        }
        return Math.log10(right);
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
