package visitors;

import tokenizer.tokens.NumberToken;
import tokenizer.tokens.Operation;
import tokenizer.tokens.Parenthesis;
import tokenizer.tokens.Token;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CalcVisitor implements TokenVisitor {
    private final Deque<Integer> stack;

    public CalcVisitor() {
        this.stack = new ArrayDeque<>();
    }

    @Override
    public void visit(NumberToken token) {
        stack.add(token.value());
    }

    @Override
    public void visit(Operation token) {
        if (stack.size() < 2) {
            throw new VisitException("Not enough arguments to calculate binary operation");
        }

        int a = stack.removeLast();
        int b = stack.removeLast();

        stack.add(token.getFunc().apply(b, a));
    }

    @Override
    public void visit(Parenthesis token) {
        throw new UnsupportedOperationException();
    }

    public int calculate(Deque<Token> rpn) {
        stack.clear();

        rpn.forEach(token -> token.accept(this));

        return stack.getLast();
    }
}
