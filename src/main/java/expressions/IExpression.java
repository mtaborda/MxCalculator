package expressions;

/**
 * Created by maximiliano on 11/10/14.
 */
public interface IExpression {

    public Double evaluate() throws InvalidEvaluationException;

    public Double subtractFrom(IExpression leftExpression) throws InvalidEvaluationException;

    public Double divideFrom(IExpression leftExpression) throws InvalidEvaluationException;
}
