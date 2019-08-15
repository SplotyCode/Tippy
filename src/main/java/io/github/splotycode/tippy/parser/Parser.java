package io.github.splotycode.tippy.parser;

import io.github.splotycode.mosaik.util.StringUtil;
import io.github.splotycode.tippy.term.*;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
            lastIdentifierStart = -1;
        }
    }

    private String currentText() {
        return input.substring(cToken.getStart(), cToken.getEnd() + 1);
    }

    private Evaluation valueToken() {
        switch (cToken.getType()) {
            case NUMBER:
                return new Constant(Double.valueOf(currentText()));
            case IDENTIFIER:
                String name = currentText();
                Token set = nextWhenType(TokenType.SET);
                if (set == null) {
                    if (skipNextType(TokenType.BRACKET_LEFT)) {
                        CallFunction functionCall = new CallFunction(name, new ArrayList<>());
                        boolean first = true;
                        while (cToken.getType() != TokenType.BRACKET_RIGHT) {
                            if (!first) {
                                needSkip(TokenType.COMMA, true);
                            }
                            functionCall.getArguments().add(valueToken());
                            needNext();
                            first = false;
                        }
                        return functionCall;
                    }
                    return new Variable(name);
                }
                needNext();
                return new SetVariable(name, valueToken());
            default:
                unexpected();
                return null;
        }
    }

    protected boolean skipNextType(TokenType type) {
        if (nextWhenType(type) == null) {
            return false;
        }
        next();
        return true;
    }

    protected Token needSkip(TokenType type, boolean followup) {
        needNext(type);
        return followup ? needNext() : next();
    }

    protected Token needNext(TokenType type) {
        Token token = needNext();
        if (token.getType() == type) {
            return token;
        }
        throw new RuntimeException("Needed " + type + " not " + token.getType());
    }

    protected Token nextWhenType(TokenType type) {
        Token peek = tokens.peek();
        if (peek == null) {
            return null;
        }
        return peek.getType() == type ? next() : null;
    }

    protected void unexpected() {
        throw new RuntimeException("Didn't expected " + cToken.getType() + " at " + cToken.position());
    }

    protected Token next() {
        return (cToken = tokens.poll());
    }

    private Token needNext() {
        next();
        if (cToken == null) throw new RuntimeException("Unexpected end");
        return cToken;
    }

    public void computeTokens() {
        next();
        base = valueToken();
    }

    public void parseTokens() {
        for (; tokenPosition < input.length(); tokenPosition++) {
            char ch = input.charAt(tokenPosition);
            if (Character.isWhitespace(ch)) {
                checkIdentifier();
                continue;
            }
            switch (ch) {
                case '(':
                    addToken(tokenPosition, TokenType.BRACKET_LEFT);
                    break;
                case ')':
                    addToken(tokenPosition, TokenType.BRACKET_RIGHT);
                    break;
                case ',':
                    addToken(tokenPosition, TokenType.COMMA);
                    break;
                case '=':
                    addToken(tokenPosition, TokenType.SET);
                    break;
                default:
                    if (lastIdentifierStart == -1) {
                        lastIdentifierStart = tokenPosition;
                    }
            }
        }
        checkIdentifier();
    }

}
