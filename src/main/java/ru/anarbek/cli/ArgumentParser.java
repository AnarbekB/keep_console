package ru.anarbek.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgumentParser {

    public static Options parse(String[] args) {
        List<String> argsList = new ArrayList<>(Arrays.asList(args));

        Options allAvailableOptions = OptionsBuilder.build();
        ArrayList<Option> options = new ArrayList<>();

        for (int i = 0; i < argsList.size(); i++) {
            Option option = allAvailableOptions.getOption(argsList.get(i));
            if (option != null) {
                if (option.isHasData()) {
                    option.addValue(argsList.get(i + 1));
                    argsList.remove(i + 1);
                }

                if (!option.hasParent()) {
                    options.add(option);
                }
            }
        }

        if (options.size() == 0) {
            //if not arguments, then print help page
            options.add(allAvailableOptions.getOption("-h"));
        }

        return new Options(options);
    }
}
