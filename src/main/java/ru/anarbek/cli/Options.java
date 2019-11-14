package ru.anarbek.cli;

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
        if (options == null) {
            return;
        }
        for (Option option : options) {
            addOption(option);
        }
    }

    private void addOption(Option opt)
    {
        if (opt == null) {
            return;
        }

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
    }

    public Option getOption(String option)
    {
        option = Util.stripLeadingHyphens(option);

        if (shortOpts.containsKey(option))
        {
            return shortOpts.get(option);
        }

        return longOpts.get(option);
    }
}
