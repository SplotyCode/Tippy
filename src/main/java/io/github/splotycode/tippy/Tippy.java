package io.github.splotycode.tippy;

import io.github.splotycode.tippy.project.MathContext;
import lombok.Getter;

import java.util.Scanner;

public class Tippy {

    @Getter private static Tippy instance = new Tippy();

    public static void main(String[] args) {}

    private Tippy() {
        MathContext base = new MathContext();
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
                    System.out.println(command);
            }
        }
    }

}
