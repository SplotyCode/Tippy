package io.github.splotycode.tippy.term.operator;

import io.github.splotycode.tippy.project.MathContext;
import io.github.splotycode.tippy.term.Evaluation;

public class MultiplEvaluation extends OperatorEvaluation {

    public MultiplEvaluation(Evaluation left, Evaluation right) {
        super(Operator.MULTIPLICATION, '*', left, right);
    }

    @Override
    public double calculate(MathContext ctx) {
        return left.calculate(ctx) * right.calculate(ctx);
    }
}
