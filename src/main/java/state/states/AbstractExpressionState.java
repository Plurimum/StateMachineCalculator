package state.states;

public abstract class AbstractExpressionState implements ExpressionState {
    protected final String expression;
    protected int position;

    protected AbstractExpressionState(String expression, int position) {
        this.expression = expression;
        this.position = position;
    }

    @Override
    public String getExpression() {
        return expression;
    }

    @Override
    public int getPosition() {
        return position;
    }
}
