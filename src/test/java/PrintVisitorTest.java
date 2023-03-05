import org.junit.jupiter.api.Test;
import tokenizer.Tokenizer;
import tokenizer.tokens.Token;
import visitors.ParserVisitor;
import visitors.PrintVisitor;

import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintVisitorTest {
    @Test
    public void testPrint() {
        String expr = "3 + 4 * 2 / (1 - 5)";
        List<Token> tokens = Tokenizer.getTokens(expr);
        Deque<Token> rpn = new ParserVisitor().parse(tokens);

        assertEquals("3 4 2 * 1 5 - / +", new PrintVisitor().print(rpn));
    }
}
