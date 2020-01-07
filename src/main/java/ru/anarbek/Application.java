package ru.anarbek;

import ru.anarbek.cli.Option;
import ru.anarbek.cli.Options;
import ru.anarbek.cli.ArgumentParser;
import ru.anarbek.view.View;
import ru.anarbek.view.ViewFactory;

public class Application {
    public static void main(String[] args) {
        try {
            Bootstrap.init();
            Options options = ArgumentParser.parse(args);
            for (Option option: options.getOptions()) {
                if (option.isHasView()) {
                    View view = ViewFactory.build(option.getArgument());
                    view.render(option, options);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
