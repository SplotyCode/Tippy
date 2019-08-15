package io.github.splotycode.tippy.term;

import io.github.splotycode.tippy.project.MathContext;

public interface Evaluation {

    double calculate(MathContext ctx);

    default String asString() {
        StringBuilder builder = new StringBuilder();
        asString(builder);
        return builder.toString();
    }

    void asString(StringBuilder builder);

}
