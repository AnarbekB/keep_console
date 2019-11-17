package ru.anarbek.helper;

import ru.anarbek.cli.Option;
import ru.anarbek.cli.Options;
import ru.anarbek.cli.OptionsBuilder;

import java.util.ArrayList;

public class ArgumentParser {

    /**
     * Пока может принемать и парсить только один аргумент, todo: расширить
     */
    public static Options parse(String[] args) {
        Options allAvailableOptions = OptionsBuilder.build();
        ArrayList<Option> options = new ArrayList<>();

        for (String argument: args) {
            Option option = allAvailableOptions.getOption(argument);
            if (option != null) {
                options.add(option);
            }
        }

        if (options.size() == 0) {
            //if not arguments, then print help page
            options.add(allAvailableOptions.getOption("-h"));
        }

        return new Options(options);
    }
}
