package expressions;

import java.util.Stack;

/**
 * Created by maximiliano on 11/10/14.
 */
public class ExpressionParser {

    static final char ADDITION = '+';
    static final char SUBTRACTION = '-';
    static final char MULTIPLICATION = '*';
    static final char DIVISION = '/';
    static final char EXPONENTIATION = '^';
    static final String LOGARITHM = "log";

    public IExpression parse(String anExpression) throws InvalidCharacterForAMathExpressionException {

        Stack<String> stack = new Stack<String>();
        String expressionStr = anExpression.replaceAll(" +", " ").trim();
        char token = '\0';
        char last = '\0';
        int i = 0;
        StringBuffer buffer = new StringBuffer();
        IExpression expression;
        IExpression subExpression = null;

        while (i<expressionStr.length() && (last=='\0' || (buffer.length()==1 && isOperator(buffer.charAt(0)))
                || !isOperator(token)) && !isLogarithm(buffer.toString())) {
            last = token;
            token = expressionStr.charAt(i);
            assertValidDigit(token);
            if (isLogarithm(buffer.toString())) {
                buffer = resetBuffer(stack, buffer);
            } else if (token=='(') {
                int close = expressionStr.indexOf(')');
                if (close>0) {
                    subExpression = parse(expressionStr.substring(i+1, close));
                    token = ')';
                }
                if (close>0) i=i+close;
            } else if (token==')') {
                buffer = resetBuffer(stack, buffer);
            } else if (token!=' ') {
                if ((!isOperator(token) || buffer.length()==0) && last!=')') {
                    buffer.append(token);
                } else if (isOperator(token)) {
                    buffer = resetBuffer(stack, buffer);
                }
            }
            i++;
        }

        resetBuffer(stack, buffer);

        if (subExpression==null) {
            expression = createExpression(token, i, expressionStr, stack);
        } else {
            expression = createExpression(token, i, expressionStr, subExpression);
        }

        return expression;
    }

    private StringBuffer resetBuffer(Stack<String> stack, StringBuffer buffer) {
        if (buffer.length()>0) {
            stack.push(buffer.toString());
        }
        return new StringBuffer();
    }

    private boolean isOperator(char aToken) {
        return aToken==ADDITION || aToken==SUBTRACTION || aToken==MULTIPLICATION ||
                aToken==DIVISION || aToken==EXPONENTIATION;
    }

    private boolean isLogarithm(String aString) {
        return aString.equals(LOGARITHM);
    }

    private void assertValidDigit(char aCharacter) throws InvalidCharacterForAMathExpressionException {
        if (!(Character.isDigit(aCharacter) || isOperator(aCharacter) || aCharacter=='(' || aCharacter==')' ||
                aCharacter==' ' || aCharacter=='.' || aCharacter=='l' || aCharacter=='o' || aCharacter=='g')) {
            throw new InvalidCharacterForAMathExpressionException(aCharacter);
        }
    }

    private IExpression createExpression(char aToken, int anIndex, String anExpressionStr, Stack<String> anStack)
            throws InvalidCharacterForAMathExpressionException {

        IExpression expression;

        if (anStack.size()>0 && isLogarithm(anStack.elementAt(0))) {
            IExpression right = parse(anExpressionStr.substring(4, anExpressionStr.length()));
            expression = new LogarithmExpression(right);
        } else {
            expression = createExpression(aToken, anIndex, anExpressionStr, new ValueExpression(anStack.pop()));
        }

        return expression;
    }

    private IExpression createExpression(char aToken, int anIndex, String anExpressionStr, IExpression aSubExpression)
            throws InvalidCharacterForAMathExpressionException {

        IExpression expression;

        if (aToken==ADDITION) {
            IExpression right = parse(anExpressionStr.substring(anIndex, anExpressionStr.length()));
            expression = new AdditionExpression(aSubExpression, right);
        } else if (aToken==SUBTRACTION) {
            IExpression right = parse(anExpressionStr.substring(anIndex, anExpressionStr.length()));
            expression = new SubtractionExpression(aSubExpression, right);
        } else if (aToken==MULTIPLICATION) {
            IExpression right = parse(anExpressionStr.substring(anIndex, anExpressionStr.length()));
            expression = new MultiplicationExpression(aSubExpression, right);
        } else if (aToken==DIVISION) {
            IExpression right = parse(anExpressionStr.substring(anIndex, anExpressionStr.length()));
            expression = new DivisionExpression(aSubExpression, right);
        } else if (aToken==EXPONENTIATION) {
            IExpression right = parse(anExpressionStr.substring(anIndex, anExpressionStr.length()));
            expression = new ExponentiationExpression(aSubExpression, right);
        } else {
            expression = aSubExpression;
        }

        return expression;
    }
}
