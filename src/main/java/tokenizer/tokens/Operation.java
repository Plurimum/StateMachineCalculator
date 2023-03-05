package tokenizer.tokens;

import visitors.TokenVisitor;

import java.util.function.BiFunction;

public class Operation implements Token {
    private final Type type;
    private final BiFunction<Integer, Integer, Integer> func;

    private Operation(Type type, BiFunction<Integer, Integer, Integer> func) {
        this.type = type;
        this.func = func;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public static Operation plus() {
        return new Operation(Type.PLUS, Integer::sum);
    }

    public static Operation minus() {
        return new Operation(Type.MINUS, (a, b) -> a - b);
    }

    public static Operation multiply() {
        return new Operation(Type.MULTIPLY, (a, b) -> a * b);
    }

    public static Operation divide() {
        return new Operation(Type.DIVIDE, (a, b) -> a / b);
    }

    public Type getType() {
        return type;
    }

    public BiFunction<Integer, Integer, Integer> getFunc() {
        return func;
    }

    public enum Type {
        PLUS("+", 1),
        MINUS("-", 1),
        MULTIPLY("*", 2),
        DIVIDE("/", 2);

        private final String value;
        private final int priority;

        Type(String value, int priority) {
            this.value = value;
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }

    @Override
    public String toString() {
        return type.value;
    }
}
