package state.states;

import tokenizer.tokens.Token;

import static tokenizer.tokens.StubToken.STUB_TOKEN;

public class EndState extends AbstractExpressionState {
    public EndState(String expression, int position) {
        super(expression, position);
    }

    @Override
    public Token createToken() {
        return STUB_TOKEN;
    }
}
