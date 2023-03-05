import tokenizer.Tokenizer;
import tokenizer.tokens.Token;
import visitors.CalcVisitor;
import visitors.ParserVisitor;
import visitors.PrintVisitor;

import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        List<Token> tokens = Tokenizer.getTokens(expression);
        Deque<Token> rpn = new ParserVisitor().parse(tokens);

        System.out.println(new PrintVisitor().print(rpn));
        System.out.println(new CalcVisitor().calculate(rpn));
    }
}
