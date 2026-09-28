package expression;

import java.util.Map;

public class Not implements Expression {
    private Expression expression;

    public Not(Expression expression) {
        this.expression = expression;
    }

    @Override
    public boolean interpret(Map<String, Integer> context) {
        return !expression.interpret(context);
    }
}
