package state;

import state.states.EndState;
import state.states.ErrorState;
import state.states.ExpressionState;
import state.states.NumberState;
import state.states.StartState;

import java.util.Set;

public class StateMachine {
    private ExpressionState state;

    private static final Set<Character> NON_DIGIT = Set.of('+', '-', '*', '/', '(', ')');

    public StateMachine(String expression) {
        this.state = new StartState(expression, 0);
    }

    public ExpressionState nextState() {
        String expression = state.getExpression();
        int position = skipWhiteSpaces(expression, state.getPosition());

        if (state instanceof EndState) {
            return state;
        }

        if (position == expression.length()) {
            state = new EndState(expression, position);

            return state;
        }

        char curChar = expression.charAt(position);

        if (Character.isDigit(curChar)) {
            state = new NumberState(expression, position);

            return state;
        }

        if (NON_DIGIT.contains(curChar) || Character.isWhitespace(curChar)) {
            state = new StartState(expression, position);

            return state;
        }

        return new ErrorState(expression, position);
    }

    private int skipWhiteSpaces(String expression, int position) {
        int result = position;

        while (result < expression.length() && Character.isWhitespace(expression.charAt(result))) {
            result++;
        }

        return result;
    }
}
