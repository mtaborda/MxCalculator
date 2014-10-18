package models;

import com.google.gson.annotations.Expose;

/**
 * Created by maximiliano on 15/10/14.
 */
public class Calculation {
    @Expose
    private String expression;
    @Expose
    private String result;

    public Calculation(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    public String getExpression() {
        return expression;
    }

    public String getResult() {
        return result;
    }
}
