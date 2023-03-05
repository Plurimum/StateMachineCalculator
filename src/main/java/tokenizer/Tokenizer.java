package tokenizer;

import state.StateException;
import state.StateMachine;
import state.states.EndState;
import state.states.ErrorState;
import state.states.ExpressionState;
import tokenizer.tokens.Token;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public static List<Token> getTokens(String expression) {
        List<Token> tokens = new ArrayList<>();
        StateMachine stateMachine = new StateMachine(expression);

        ExpressionState state = stateMachine.nextState();

        while (!(state instanceof EndState)) {
            if (state instanceof ErrorState errorState) {
                throw new StateException(errorState.getExpression(), errorState.getPosition());
            }

            tokens.add(state.createToken());
            state = stateMachine.nextState();
        }

        return tokens;
    }
}
