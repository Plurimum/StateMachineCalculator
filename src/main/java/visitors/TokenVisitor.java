package visitors;

import tokenizer.tokens.NumberToken;
import tokenizer.tokens.Operation;
import tokenizer.tokens.Parenthesis;

public interface TokenVisitor {
    void visit(NumberToken token);

    void visit(Operation token);

    void visit (Parenthesis token);
}
