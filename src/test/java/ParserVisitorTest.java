import org.junit.jupiter.api.Test;
import tokenizer.Tokenizer;
import tokenizer.tokens.Token;
import visitors.ParserVisitor;
import visitors.VisitException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserVisitorTest {
    @Test
    public void testCorrectExpression() {
        List<String> expected = List.of("3", "4", "2", "*", "1", "5", "-", "/", "+");

        String expr = "3 + 4 * 2 / (1 - 5)";
        List<Token> tokens = Tokenizer.getTokens(expr);
        List<String> actual = new ParserVisitor().parse(tokens).stream()
                .map(Token::toString)
                .toList();

        assertIterableEquals(expected, actual);
    }

    @Test
    public void testIncorrectExpression() {
        List<Token> tokens = Tokenizer.getTokens("))(3 + 4 * 2 / (1 - 5)");

        assertThrows(VisitException.class, () -> new ParserVisitor().parse(tokens));
    }
}
