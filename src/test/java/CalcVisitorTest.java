import org.junit.jupiter.api.Test;
import tokenizer.Tokenizer;
import tokenizer.tokens.Token;
import visitors.CalcVisitor;
import visitors.ParserVisitor;

import java.util.Deque;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalcVisitorTest {
    private static final Map<String, Integer> CORRECT_EXPRESSIONS = Map.of(
            "2 + 2 * 2", 6,
            "3 + 4 * 2 / (1 - 5)", 1,
            "(((((1 + 1))))) + 1", 3,
            "(1 + 2) - 3 * 4", -9,
            "5", 5,
            "     5         + 5 - 3\t\t\t*4", -2
    );

    private static final List<String> INCORRECT_EXPRESSIONS = List.of(
            "2 +",
            "-2",
            "2 ^ 5",
            "kek",
            "12345 + kek",
            "(123 + 1) * kek",
            ""
    );

    @Test
    public void testCorrectExpressions() {
        ParserVisitor parserVisitor = new ParserVisitor();
        CalcVisitor calcVisitor = new CalcVisitor();

        CORRECT_EXPRESSIONS.forEach((expr, ans) -> {
            System.out.println(expr);
            Deque<Token> rpn = parserVisitor.parse(Tokenizer.getTokens(expr));

            assertEquals(ans, calcVisitor.calculate(rpn));
        });
    }

    @Test
    public void testIncorrectExpressions() {
        ParserVisitor parserVisitor = new ParserVisitor();
        CalcVisitor calcVisitor = new CalcVisitor();

        INCORRECT_EXPRESSIONS.forEach(expr ->
                assertThrows(
                        RuntimeException.class,
                        () -> {
                            Deque<Token> rpn = parserVisitor.parse(Tokenizer.getTokens(expr));
                            calcVisitor.calculate(rpn);
                        }
                )
        );
    }
}
