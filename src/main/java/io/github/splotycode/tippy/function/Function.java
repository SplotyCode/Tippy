package io.github.splotycode.tippy.function;

import io.github.splotycode.tippy.project.MathContext;
import lombok.Data;

@Data
public abstract class Function {

    protected String name;
    protected String[] argumentNames;

    public Function(String name, String... argumentNames) {
        this.name = name;
        this.argumentNames = argumentNames;
    }

    public int argSize() {
        return argumentNames.length;
    }

    public abstract double invoke(MathContext context, double... arguments);

}
