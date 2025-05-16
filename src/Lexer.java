import java.util.ArrayList;
import java.util.List;

public class Lexer {

    String source;
    int curr = 0;

    public Lexer(String source) {
        this.source = source;
    }

    public Token eatToken() throws ScanningException {

        if (isAtEnd())
            return new Token("", TokenType.END, '\0', '\0', 0);

        final int linePos = curr;
        final char c = source.charAt(curr++);

        return switch (c) {
            case '\\' -> eatEscapeToken();
            case '[' -> eatRangeToken();
            case '(' -> new Token(String.valueOf(c), TokenType.OPEN_PAREN, c, c, linePos);
            case ')' -> new Token(String.valueOf(c), TokenType.CLOSE_PAREN, c, c, linePos);
            case '|' -> new Token(String.valueOf(c), TokenType.PIPE, c, c, linePos);
            case '*' -> new Token(String.valueOf(c), TokenType.STAR, c, c, linePos);
            case '+' -> new Token(String.valueOf(c), TokenType.PLUS, c, c, linePos);
            case '?' -> new Token(String.valueOf(c), TokenType.QUESTION_MARK, c, c, linePos);
            case '.' -> new Token(String.valueOf(c), TokenType.CHAR, '\0', '\127', linePos);
            default -> new Token(String.valueOf(c), TokenType.CHAR, c, c, linePos);
        };
    }

    public void printTokens() {
        try {
            List<Token> tokens = scanTokens();
            for (Token t : tokens) {
                System.out.println(t);
            }
        } catch (ScanningException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Token> scanTokens() throws ScanningException {
        List<Token> tokens = new ArrayList<>();
        Token t;

        do {
            t = eatToken();
            tokens.add(t);
        } while (t.type() != TokenType.END);

        return tokens;
    }

    // TODO: improve code
    private Token eatRangeToken() throws ScanningException {

        if (curr + 3 >= source.length())
            throw new ScanningException("expected range after '['", source, curr);

        final String lexeme = source.substring(curr - 1, curr + 4); // '[a-z]'

        char min = source.charAt(1);

        if (source.charAt(2) != '-')
            throw new ScanningException("expected '-' in range expression", source, curr + 1);

        char max = source.charAt(3);

        if (source.charAt(4) != ']')
            throw new ScanningException("expected ']' at end of range expression", source, curr + 2);

        curr += 4;
        return new Token(lexeme, TokenType.CHAR, min, max, curr - 4);
    }

    private Token eatEscapeToken() throws ScanningException {

        if (isAtEnd())
            throw new ScanningException("expected letter after '\\'", source, curr - 1);

        String lexeme = source.substring(curr - 1, curr + 1);
        char c = source.charAt(curr++);
        return new Token(lexeme, TokenType.CHAR, c, c, curr - 2);
    }

    private boolean isAtEnd() {
        return curr >= source.length();
    }
}