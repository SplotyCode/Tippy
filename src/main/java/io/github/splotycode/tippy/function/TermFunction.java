package io.github.splotycode.tippy.function;

import io.github.splotycode.tippy.project.MathContext;
import io.github.splotycode.tippy.term.Evaluation;

public class TermFunction extends Function implements Evaluation {

    private Evaluation evaluation;

    public TermFunction(String name, Evaluation evaluation, String... argumentNames) {
        super(name, argumentNames);
        this.evaluation = evaluation;
    }

    @Override
    public double invoke(MathContext context, double... arguments) {
        MathContext ctx = new MathContext(context);
        for (int i = 0; i < argumentNames.length; i++) {
            ctx.setVariable(argumentNames[i], arguments[i]);
        }
        return calculate(ctx);
    }

    @Override
    public double calculate(MathContext ctx) {
        return evaluation.calculate(ctx);
    }

    @Override
    public void asString(StringBuilder builder) {
        builder.append("method body???");
    }
}
