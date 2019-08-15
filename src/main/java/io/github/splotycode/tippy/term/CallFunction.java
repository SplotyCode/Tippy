package io.github.splotycode.tippy.term;

import io.github.splotycode.mosaik.util.StringUtil;
import io.github.splotycode.tippy.project.MathContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CallFunction implements Evaluation {

    private String name;
    private List<Evaluation> arguments;

    public double calculate(MathContext ctx) {
        double[] args = new double[arguments.size()];
        for (int i = 0; i < arguments.size(); i++) {
            args[i] = arguments.get(i).calculate(ctx);
        }
        return ctx.callFunction(name, args);
    }

    @Override
    public void asString(StringBuilder builder) {
        builder.append(name).append('(');
        StringUtil.join(builder, arguments, (sb, evaluation) -> evaluation.asString(sb), ", ", false);
        builder.append(')');
    }
}
