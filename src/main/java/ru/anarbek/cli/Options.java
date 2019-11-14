package ru.anarbek.cli;

import ru.anarbek.constant.Argument;
import ru.anarbek.helper.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Options {

    private final Map<String, Option> shortOpts = new LinkedHashMap<String, Option>();

    private final Map<String, Option> longOpts = new LinkedHashMap<String, Option>();

    private final List<Object> requiredOpts = new ArrayList<Object>();

    public Options(ArrayList<Option> options) {
        for (Option option : options) {
            addOption(option);
        }
    }

    public Options addOption(String name, String longName, boolean required, String description, Argument argument)
    {
        addOption(new Option(name, longName, required, description, argument));
        return this;
    }

    public Options addOption(Option opt)
    {
        String key = opt.getName();

        // add it to the long option list
        if (opt.hasLongOpt())
        {
            longOpts.put(opt.getLongName(), opt);
        }

        // if the option is required add it to the required list
        if (opt.isRequired())
        {
            if (requiredOpts.contains(key))
            {
                requiredOpts.remove(requiredOpts.indexOf(key));
            }
            requiredOpts.add(key);
        }

        shortOpts.put(key, opt);

        return this;
    }

    public Option getOption(String option)
    {
        //пока команды хранятся с префиксом -- или -
        //option = Util.stripLeadingHyphens(option);

        if (shortOpts.containsKey(option))
        {
            return shortOpts.get(option);
        }

        return longOpts.get(option);
    }
}
