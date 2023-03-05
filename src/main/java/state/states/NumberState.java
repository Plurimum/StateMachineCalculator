package state.states;

import tokenizer.tokens.NumberToken;
import tokenizer.tokens.Token;

public class NumberState extends AbstractExpressionState {
    public NumberState(String expression, int position) {
        super(expression, position);
    }

    @Override
    public Token createToken() {
        int numberBeginPosition = position;

        while (position < expression.length() && Character.isDigit(expression.charAt(position))) {
            position++;
        }

        return new NumberToken(Integer.parseInt(expression.substring(numberBeginPosition, position)));
    }
}
