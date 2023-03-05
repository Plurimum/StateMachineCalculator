package state.states;

import tokenizer.tokens.Token;

public interface ExpressionState {
    Token createToken();

    String getExpression();

    int getPosition();
}
