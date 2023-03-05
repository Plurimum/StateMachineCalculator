package tokenizer.tokens;

import visitors.TokenVisitor;

public class Parenthesis implements Token {
    private final Type type;

    private Parenthesis(Type type) {
        this.type = type;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public static Parenthesis openParenthesis() {
        return new Parenthesis(Type.OPEN);
    }

    public static Parenthesis closeParenthesis() {
        return new Parenthesis(Type.CLOSE);
    }

    public enum Type {
        OPEN("("), CLOSE(")");

        private final String value;

        Type(String value) {
            this.value = value;
        }
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.value;
    }
}
