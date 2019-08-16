package io.github.splotycode.tippy;

import io.github.splotycode.mosaik.util.prettyprint.PrettyPrint;
import io.github.splotycode.tippy.gui.TippyWindow;
import io.github.splotycode.tippy.parser.Parser;
import io.github.splotycode.tippy.project.MathContext;
import io.github.splotycode.tippy.project.Settings;
import io.github.splotycode.tippy.term.Evaluation;
import lombok.Getter;

import java.util.Scanner;

@Getter
public class Tippy {

    @Getter private static Tippy instance = new Tippy();

    private MathContext base = new MathContext();
    private TippyWindow window = new TippyWindow();

    public static void main(String[] args) {}

    public String exec(String input) {
        StringBuilder builder = new StringBuilder();
        Parser parser = new Parser(input);
        Evaluation evaluation;
        String evaluationString;
        try {
            parser.parseTokens();
            if (base.getSettings().isDebugTokens()) {
                builder.append(parser.getTokens()).append("\n");
            }
            parser.computeTokens();
            evaluation = parser.getBase();
            evaluationString = evaluation.asString();
        } catch (Throwable error) {
            builder.append("Error: ").append(error.getMessage());
            error.printStackTrace();
            return builder.toString();
        }
        String result;
        try {
            result = String.valueOf(evaluation.calculate(base));
        } catch (Throwable error) {
            result = "Error: " + error.getMessage();
        }
        builder.append(evaluationString).append(" -> ").append(result);
        if (base.getSettings().isDebugTermTree()) {
            builder.append(new PrettyPrint(evaluation).prettyPrint());
        }
        return builder.toString();
    }

    private Tippy() {
        base.setSettings(new Settings());
        if (true) {
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
                        System.out.println(exec(command));
                }
            }
        }
    }

}
