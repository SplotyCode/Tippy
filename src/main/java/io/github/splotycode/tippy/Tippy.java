package io.github.splotycode.tippy;

import io.github.splotycode.mosaik.util.prettyprint.PrettyPrint;
import io.github.splotycode.tippy.parser.Parser;
import io.github.splotycode.tippy.project.MathContext;
import io.github.splotycode.tippy.project.Settings;
import io.github.splotycode.tippy.term.Evaluation;
import lombok.Getter;

import java.util.Scanner;

public class Tippy {

    @Getter private static Tippy instance = new Tippy();

    public static void main(String[] args) {}

    private Tippy() {
        MathContext base = new MathContext();
        base.setSettings(new Settings());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("->");
            String command = scanner.nextLine();
            switch (command.toLowerCase()) {
                case "close":
                case "exit":
                    System.exit(0);
                    break;
                default:
                    Parser parser = new Parser(command);
                    Evaluation evaluation;
                    String evaluationString;
                    try {
                        parser.parseTokens();
                        if (base.getSettings().isDebugTokens()) {
                            System.out.println(parser.getTokens());
                        }
                        parser.computeTokens();
                        evaluation = parser.getBase();
                        evaluationString = evaluation.asString();
                    } catch (Throwable error) {
                        System.out.println("Error: " + error.getMessage());
                        break;
                    }
                    String result;
                    try {
                        result = String.valueOf(evaluation.calculate(base));
                    } catch (Throwable error) {
                        result = "Error: " + error.getMessage();
                    }
                    System.out.println(evaluationString + " -> " + result);
                    if (base.getSettings().isDebugTermTree()) {
                        System.out.println(new PrettyPrint(evaluation).prettyPrint());
                    }
            }
        }
    }

}
