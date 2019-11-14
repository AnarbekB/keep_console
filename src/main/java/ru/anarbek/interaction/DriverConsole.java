package ru.anarbek.interaction;

import java.io.PrintStream;

public class DriverConsole implements Interaction {

    private PrintStream out;

    public DriverConsole() {
        this.out = System.out;
    }

    public String input(String message) {
        return null;
    }

    public void output(String output, boolean ln) {
        if (ln) {
            this.out.println(output);
        } else {
            this.out.print(output);
        }
    }
}
