package io.github.splotycode.tippy.term.operator;

import io.github.splotycode.tippy.project.MathContext;
import io.github.splotycode.tippy.term.Evaluation;

public class PlusEvaluation extends OperatorEvaluation {

    public PlusEvaluation(Evaluation left, Evaluation right) {
        super(Operator.ADDITION, '+', left, right);
    }

    @Override
    public double calculate(MathContext ctx) {
        return left.calculate(ctx) + right.calculate(ctx);
    }
}
