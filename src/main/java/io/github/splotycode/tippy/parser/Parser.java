package io.github.splotycode.tippy.parser;

import io.github.splotycode.mosaik.util.StringUtil;
import io.github.splotycode.tippy.term.Constant;
import io.github.splotycode.tippy.term.Evaluation;
import io.github.splotycode.tippy.term.Variable;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Queue;

@Getter
public class Parser {

    private String input;
    private Queue<Token> tokens = new ArrayDeque<>();
    private int lastIdentifierStart = -1;
    private int tokenPosition;
    private Evaluation base;
    private Token cToken;

    public Parser(String input) {
        this.input = input;
    }

    private void addToken(int position, TokenType type) {
        checkIdentifier();
        tokens.add(new Token(position, type));
    }

    private void checkIdentifier() {
        if (lastIdentifierStart != -1) {
            boolean number = StringUtil.isDouble(input.substring(lastIdentifierStart, tokenPosition));
            tokens.add(new Token(lastIdentifierStart, tokenPosition - 1, number ? TokenType.NUMBER : TokenType.IDENTIFIER));
        }
    }

    private String currentText() {
        return input.substring(cToken.getStart(), cToken.getEnd() + 1);
    }

    public void computeTokens() {
        while ((cToken = tokens.poll()) != null) {
            switch (cToken.getType()) {
                case NUMBER:
                    base = new Constant(Double.valueOf(currentText()));
                    break;
                case IDENTIFIER:
                    base = new Variable(currentText());
                    break;
                default:
                    throw new RuntimeException("Didn't expected " + cToken.getType() + " at " + cToken.position());
            }
        }
    }

    public void parseTokens() {
        for (; tokenPosition < input.length(); tokenPosition++) {
            char ch = input.charAt(tokenPosition);
            if (Character.isWhitespace(ch)) {
                checkIdentifier();
                break;
            }
            switch (ch) {
                case '(':
                    addToken(tokenPosition, TokenType.BRACKET_LEFT);
                    break;
                case '=':
                    addToken(tokenPosition, TokenType.SET);
                    break;
                default:
                    lastIdentifierStart = tokenPosition;
            }
        }
        checkIdentifier();
    }

}
