package expressions;

/**
 * Created by maximiliano on 12/10/14.
 */
public class InvalidCharacterForAMathExpressionException extends Exception {
    private char character;

    public InvalidCharacterForAMathExpressionException(char aCharacter) {
        character = aCharacter;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    @Override
    public String getMessage() {
        return character+ " is invalid for a math expression";
    }
}
