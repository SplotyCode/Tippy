package io.github.splotycode.tippy.term;

import io.github.splotycode.tippy.project.MathContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Constant implements Evaluation {

    private double constant;

    public double calculate(MathContext ctx) {
        return constant;
    }

    @Override
    public void asString(StringBuilder builder) {
        builder.append(constant);
    }
}
