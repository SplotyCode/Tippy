package io.github.splotycode.tippy.term.operator;

import io.github.splotycode.tippy.project.MathContext;
import io.github.splotycode.tippy.term.Evaluation;

public class MinusEvaluation extends OperatorEvaluation {

    public MinusEvaluation(Evaluation left, Evaluation right) {
        super(Operator.SUBTRACTION, '-', left, right);
    }

    @Override
    public double calculate(MathContext ctx) {
        return left.calculate(ctx) - right.calculate(ctx);
    }
}
