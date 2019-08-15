package io.github.splotycode.tippy.term;

import io.github.splotycode.tippy.project.MathContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Negate implements Evaluation {

    private Evaluation wrapped;

    @Override
    public double calculate(MathContext ctx) {
        return -wrapped.calculate(ctx);
    }

    @Override
    public void asString(StringBuilder builder) {
        builder.append('-');
        wrapped.asString(builder);
    }
}
