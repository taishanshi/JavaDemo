package expression;

import java.util.Map;

public class GreaterThan implements Expression {
    private String variable;
    private int threshold;

    public GreaterThan(String variable, int threshold) {
        this.variable = variable;
        this.threshold = threshold;
    }

    @Override
    public boolean interpret(Map<String, Integer> context) {
        return context.getOrDefault(variable, 0) > threshold;
    }
}
