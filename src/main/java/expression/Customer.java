package expression;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    public static void main(String[] args) {
        // 构建表达式：age > 18 AND (city = "北京" OR city = "上海")
        // 这里用 score > 60 和 level > 3 模拟
        Expression expr = new And(
                new GreaterThan("age", 18),
                new Or(
                        new GreaterThan("level", 3),
                        new GreaterThan("score", 90)
                )
        );

        // 设置上下文
        Map<String, Integer> context = new HashMap<>();
        context.put("age", 25);
        context.put("level", 2);
        context.put("score", 95);

        System.out.println("结果: " + expr.interpret(context));  // true

    }
}
