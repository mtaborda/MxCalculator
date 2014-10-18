package expressions;

/**
 * Created by maximiliano on 11/10/14.
 */
public class DivisionExpression implements IExpression {
    private IExpression left;
    private IExpression right;

    public DivisionExpression(IExpression anExpression, IExpression otherExpression) {
        left = anExpression;
        right = otherExpression;
    }

    @Override
    public Double evaluate() throws InvalidEvaluationException {
        return right.divideFrom(left);
    }

    @Override
    public Double subtractFrom(IExpression leftExpression) throws InvalidEvaluationException {
        return leftExpression.evaluate() - evaluate();
    }

    @Override
    public Double divideFrom(IExpression leftExpression) throws InvalidEvaluationException {
        return leftExpression.evaluate() / left.evaluate() / right.evaluate();
    }
}
