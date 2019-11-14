package ru.anarbek.helper;

import ru.anarbek.cli.Option;
import ru.anarbek.cli.Options;
import ru.anarbek.cli.OptionsBuilder;

public class ArgumentParser {

    /**
     * Пока может принемать и парсить только один аргумент, todo: расширить
     */
    public static Option parse(String[] args) {
        Options options = OptionsBuilder.build();

        for (String argument: args) {
            Option option = options.getOption(argument);
            if (option != null) {
                return option;
            }
        }

        return options.getOption("menu");
    }
}
