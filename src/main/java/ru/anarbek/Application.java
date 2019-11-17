package ru.anarbek;

import ru.anarbek.cli.Option;
import ru.anarbek.cli.Options;
import ru.anarbek.helper.ArgumentParser;
import ru.anarbek.view.Page;
import ru.anarbek.view.ViewFactory;

public class Application {
    public static void main(String[] args) {
        try {
            Options options = ArgumentParser.parse(args);
            for (Option option: options.getOptions()) {
                Page page = ViewFactory.build(option.getArgument());
                page.render();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
