package tokenizer.tokens;

import visitors.TokenVisitor;

public interface Token {
    void accept(TokenVisitor tokenVisitor);
}
