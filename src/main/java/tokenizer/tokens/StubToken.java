package tokenizer.tokens;

import visitors.TokenVisitor;

public class StubToken implements Token {
    public static final StubToken STUB_TOKEN = new StubToken();

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        // do nothing
    }
}
