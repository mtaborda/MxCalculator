package expressions;

/**
 * Created by maximiliano on 11/10/14.
 */
public class InvalidEvaluationException extends Exception {
    private String message;

    public InvalidEvaluationException(String aMessage) {
        message = aMessage;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    @Override
    public String getMessage() {
        return "Invalid evaluation <" +message+ ">";
    }
}
