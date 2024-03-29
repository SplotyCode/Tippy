package io.github.splotycode.tippy.project;

import io.github.splotycode.tippy.function.Function;
import lombok.Setter;

import java.util.HashMap;

public class MathContext {

    private MathContext parent;
    @Setter private Settings settings;

    private HashMap<String, Double> vairables = new HashMap<>();
    private HashMap<String, HashMap<Integer, Function>> functions = new HashMap<>();

    public MathContext() {
        putDefaults();
    }

    public MathContext(MathContext parent) {
        this.parent = parent;
    }

    protected void putDefaults() {
        setVariable("PI", Math.PI);
        setVariable("e", Math.E);

        putFunction(new Function("sin", "x") {
            @Override
            public double invoke(MathContext context, double[] arguments) {
                double value = arguments[0];
                if (getSettings().getAngleType() == AngleType.DEGREES) {
                    value = Math.toRadians(value);
                }
                return Math.sin(value);
            }
        });
    }

    public double loadVariable(String name) {
        Double variable;
        MathContext current = this;
        while ((variable = current.vairables.get(name)) == null && current.parent != null) {
            current = current.parent;
        }
        if (variable == null) throw new RuntimeException("No variable named '" + name + "' found in the current context");
        return variable;
    }

    public void setVariable(String name, double value) {
        vairables.put(name, value);
    }

    public void putFunction(Function function) {
        functions.computeIfAbsent(function.getName(), s -> new HashMap<>(4)).putIfAbsent(function.argSize(), function);
    }

    public boolean exsitsFunction(String name, int argCount) {
        if (parent != null) {
            return parent.exsitsFunction(name, argCount);
        }
        HashMap<Integer, Function> data;
        return (data = functions.get(name)) != null && data.containsKey(argCount);
    }

    public double callFunction(String name, double... arguments) {
        if (parent != null) {
            return parent.callFunction(name, arguments);
        }
        HashMap<Integer, Function> data = functions.get(name);
        if (data == null) throw new IllegalArgumentException("No function with name " + name);
        Function function = data.get(arguments.length);
        if (function == null) throw new IllegalArgumentException("No function with that amount of parameters: " + arguments.length);
        return function.invoke(this, arguments);
    }

    public MathContext baseParent() {
        MathContext current = this;
        while (current.parent != null) {
            current = current.parent;
        }
        return current;
    }

    public Settings getSettings() {
        MathContext current = this;
        while (current != null) {
            if (current.settings != null) {
                return current.settings;
            }
            current = current.parent;
        }
        return null;
    }
}
