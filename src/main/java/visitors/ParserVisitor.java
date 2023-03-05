package visitors;

import tokenizer.tokens.NumberToken;
import tokenizer.tokens.Operation;
import tokenizer.tokens.Parenthesis;
import tokenizer.tokens.Token;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static tokenizer.tokens.Parenthesis.Type.OPEN;

public class ParserVisitor implements TokenVisitor {
    private final Deque<Token> rpn;
    private final Deque<Token> stack;

    public ParserVisitor() {
        this.rpn = new ArrayDeque<>();
        this.stack = new ArrayDeque<>();
    }

    @Override
    public void visit(NumberToken token) {
        rpn.add(token);
    }

    @Override
    public void visit(Operation token) {
        while (stack.peekLast() instanceof Operation operation &&
            operation.getType().getPriority() >= token.getType().getPriority()
        ) {
            rpn.add(stack.removeLast());
        }

        stack.add(token);
    }

    @Override
    public void visit(Parenthesis token) {
        switch (token.getType()) {
            case OPEN -> stack.add(token);
            case CLOSE -> {
                while (!stack.isEmpty() &&
                        (stack.peekLast() instanceof Parenthesis parenthesis &&
                        parenthesis.getType() != OPEN ||
                        !(stack.peekLast() instanceof Parenthesis))
                ) {
                    rpn.add(stack.removeLast());
                }

                if (stack.isEmpty()) {
                    throw new VisitException("Incorrect parenthesis sequence");
                }

                stack.removeLast();
            }
        }
    }

    public Deque<Token> parse(List<Token> tokens) {
        rpn.clear();
        stack.clear();

        tokens.forEach(token -> token.accept(this));

        while (!stack.isEmpty()) {
            rpn.add(stack.removeLast());
        }

        return rpn;
    }
}
