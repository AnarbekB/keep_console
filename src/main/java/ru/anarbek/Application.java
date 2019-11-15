package ru.anarbek;

import ru.anarbek.cli.Option;
import ru.anarbek.helper.ArgumentParser;
import ru.anarbek.view.Page;
import ru.anarbek.view.ViewFactory;

public class Application {
    public static void main(String[] args) {
        try {
            Option option = ArgumentParser.parse(args);
            Page page = ViewFactory.build(option.getArgument());
            page.render();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
