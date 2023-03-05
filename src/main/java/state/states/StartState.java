package state.states;

import state.StateException;
import tokenizer.tokens.Token;

import static tokenizer.tokens.Operation.divide;
import static tokenizer.tokens.Operation.minus;
import static tokenizer.tokens.Operation.multiply;
import static tokenizer.tokens.Operation.plus;
import static tokenizer.tokens.Parenthesis.closeParenthesis;
import static tokenizer.tokens.Parenthesis.openParenthesis;

public class StartState extends AbstractExpressionState {
    public StartState(String expression, int position) {
        super(expression, position);
    }

    @Override
    public Token createToken() {
        return switch (expression.charAt(position++)) {
            case '+' -> plus();
            case '-' -> minus();
            case '*' -> multiply();
            case '/' -> divide();
            case '(' -> openParenthesis();
            case ')' -> closeParenthesis();
            default -> throw new StateException(expression, position);
        };
    }
}
