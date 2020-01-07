package ru.anarbek.cli;

import ru.anarbek.constant.Argument;
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
                //check if option can contain data(text)
                if (option.isHasData()) {
                    option.setOptionPresent(true);

                    //check if data is option, i.e. data(text) missing
                    if (Util.isOption(argsList.get(i + 1))) {
                        throw new InputArgumentsException(
                                "For option: " + argsList.get(i) + " need data, you input argument: " +
                                        argsList.get(i + 1)
                        );
                    }

                    StringBuilder stringBuilder = new StringBuilder(argsList.get(i + 1));
                    argsList.remove(i + 1);
                    int j = i + 1;
                    if (argsList.size() > j) {
                        while (!Util.isOption(argsList.get(j))) {
                            stringBuilder.append(" ");
                            stringBuilder.append(argsList.get(j));
                            argsList.remove(j);
                            if (argsList.size() - 1 < j) {
                                break;
                            }
                        }
                    }

                    option.addValue(stringBuilder.toString());
                } else if (option.isHasBoolean()) {
                    option.setOptionPresent(true);
                }

                if (!option.hasParent()) {
                    options.add(option);
                }
            }
        }

        if (options.size() == 0) {
            //if not arguments, then print help page
            options.add(allAvailableOptions.getOption(Argument.HELP));
        }

        return new Options(options);
    }
}
