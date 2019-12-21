package ru.anarbek.cli;

import ru.anarbek.helper.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgumentParser {

    public static Options parse(String[] args) throws LoadFileException, InputArgumentsException {
        List<String> argsList = new ArrayList<>(Arrays.asList(args));

        Options allAvailableOptions = OptionsBuilder.build();
        ArrayList<Option> options = new ArrayList<>();

        for (int i = 0; i < argsList.size(); i++) {
            Option option = allAvailableOptions.getOption(argsList.get(i));
            if (option != null) {
                //option expects data
                if (option.isHasData()) {
                    //Are data an option
                    if (Util.isOption(argsList.get(i + 1))) {
                        throw new InputArgumentsException(
                                "For option: " + argsList.get(i) + " need data, you input argument: " +
                                        argsList.get(i + 1)
                        );
                    }

                    String text = argsList.get(i + 1);
                    argsList.remove(i + 1);
                    int j = i + 1;
                    if (argsList.size() >= j) {
                        while (!Util.isOption(argsList.get(j))) {
                            //todo StringBuilder и разнести логику наполнения данными опций
                            text = text + " " + argsList.get(j);
                            argsList.remove(j);
                            if (argsList.size() - 1 < j) {
                                break;
                            }
                        }
                    }

                    option.addValue(text);
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
