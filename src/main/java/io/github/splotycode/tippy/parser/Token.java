package io.github.splotycode.tippy.parser;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Token {

    private int start, end;
    private TokenType type;

    public Token(int start, TokenType type) {
        this(start, start, type);
    }

    public String position() {
        if (end - start > 0) {
            return (start + 1) + " - " + (end + 1);
        }
        return String.valueOf(end + 1);
    }
}
