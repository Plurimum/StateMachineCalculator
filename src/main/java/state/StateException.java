package state;

public class StateException extends RuntimeException {
    public StateException(String expression, int position) {
        super(getErrorMessage(expression, position));
    }

    private static String getErrorMessage(String expression, int position) {
        return String.format(
                "Unexpected token:%n%s%n%s%n",
                expression,
                " ".repeat(position) + "^"
        );
    }
}
