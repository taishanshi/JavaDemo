package expression;

import java.util.Map;

public interface Expression {
    boolean interpret(Map<String, Integer> context);
}
