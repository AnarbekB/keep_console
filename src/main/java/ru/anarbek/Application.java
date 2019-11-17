package ru.anarbek;

import ru.anarbek.cli.Option;
import ru.anarbek.cli.Options;
import ru.anarbek.cli.ArgumentParser;
import ru.anarbek.view.View;
import ru.anarbek.view.ViewFactory;

public class Application {
    public static void main(String[] args) {
        try {
            Options options = ArgumentParser.parse(args);
            for (Option option: options.getOptions()) {
                View view = ViewFactory.build(option.getArgument());
                view.render();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
