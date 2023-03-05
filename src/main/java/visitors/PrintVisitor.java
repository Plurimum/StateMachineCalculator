package visitors;

import tokenizer.tokens.NumberToken;
import tokenizer.tokens.Operation;
import tokenizer.tokens.Parenthesis;
import tokenizer.tokens.Token;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PrintVisitor implements TokenVisitor {
    private final List<String> stringTokens;

    public PrintVisitor() {
        this.stringTokens = new ArrayList<>();
    }

    @Override
    public void visit(NumberToken token) {
        stringTokens.add(token.toString());
    }

    @Override
    public void visit(Operation token) {
        stringTokens.add(token.toString());
    }

    @Override
    public void visit(Parenthesis token) {
        stringTokens.add(token.toString());
    }

    public String print(Deque<Token> rpn) {
        stringTokens.clear();

        rpn.forEach(token -> token.accept(this));

        return String.join(" ", stringTokens);
    }
}
