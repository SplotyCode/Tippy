package io.github.splotycode.tippy.term;

import io.github.splotycode.tippy.project.MathContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Variable implements Evaluation {

    private String name;

    public double calculate(MathContext ctx) {
        return ctx.loadVariable(name);
    }

    @Override
    public void asString(StringBuilder builder) {
        builder.append(name);
    }
}
