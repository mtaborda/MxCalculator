package expressions;

/**
 * Created by maximiliano on 11/10/14.
 */
public class AdditionExpression implements IExpression {
    private IExpression left;
    private IExpression right;

    public AdditionExpression(IExpression anExpression, IExpression otherExpression) {
        left = anExpression;
        right = otherExpression;
    }

    @Override
    public Double evaluate() throws InvalidEvaluationException {
        return left.evaluate() + right.evaluate();
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
