package tokenizer.tokens;

import visitors.TokenVisitor;

public record NumberToken(int value) implements Token {
    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
