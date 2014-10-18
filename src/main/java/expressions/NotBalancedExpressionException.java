package expressions;

/**
 * Created by maximiliano on 12/10/14.
 */
public class NotBalancedExpressionException extends Exception {
    private String expression;

    public NotBalancedExpressionException(String anExpression) {
        expression = anExpression;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    @Override
    public String getMessage() {
        return "The expression <" +expression+ "> is not balanced";
    }
}
