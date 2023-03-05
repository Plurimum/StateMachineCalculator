import org.junit.jupiter.api.Test;
import state.StateException;
import tokenizer.Tokenizer;
import tokenizer.tokens.Token;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TokenizerTest {
    private static final List<String> INCORRECT_EXPRESSIONS = List.of(
            "2 ^ 5",
            "kek",
            "12345 + kek",
            "(123 + 1) * kek"
    );

    @Test
    public void testCorrectExpression() {
        String expr = "3 + 4 * 2 / (1 - 5)";
        List<String> expectedTokens = List.of(
                "3", "+", "4", "*", "2", "/", "(", "1", "-", "5", ")"
        );
        List<String> actualTokens = Tokenizer.getTokens(expr).stream()
                .map(Token::toString)
                .toList();

        assertIterableEquals(expectedTokens, actualTokens);
    }

    @Test
    public void testIncorrectExpressions() {
        INCORRECT_EXPRESSIONS.forEach(expr ->
                assertThrows(
                        StateException.class,
                        () -> Tokenizer.getTokens(expr)
                )
        );
    }
}
