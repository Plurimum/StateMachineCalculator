package state.states;

import tokenizer.tokens.Token;

import static tokenizer.tokens.StubToken.STUB_TOKEN;

public class ErrorState extends AbstractExpressionState {
    public ErrorState(String expression, int position) {
        super(expression, position);
    }

    @Override
    public Token createToken() {
        return STUB_TOKEN;
    }
}
