package io.github.splotycode.tippy.term;

import io.github.splotycode.tippy.project.MathContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SetVariable implements Evaluation {

    private String name;
    private Evaluation value;

    @Override
    public double calculate(MathContext ctx) {
        double set = value.calculate(ctx);
        ctx.setVariable(name, set);
        return set;
    }

    @Override
    public void asString(StringBuilder builder) {
        builder.append(name).append(" = ");
        value.asString(builder);
    }
}
