package io.github.splotycode.tippy.term.operator;

import io.github.splotycode.tippy.term.Evaluation;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public abstract class OperatorEvaluation implements Evaluation {

    private Operator operator;
    private char operatorChar;
    protected Evaluation left;
    protected Evaluation right;

    @Override
    public void asString(StringBuilder builder) {
        left.asString(builder);
        builder.append(' ').append(operatorChar).append(' ');
        right.asString(builder);
    }
}
