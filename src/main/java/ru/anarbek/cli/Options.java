package ru.anarbek.cli;

import ru.anarbek.helper.Util;

import java.util.*;

public class Options {

    private final Map<String, Option> shortOpts = new LinkedHashMap<>();

    private final Map<String, Option> longOpts = new LinkedHashMap<>();

    private final List<Object> requiredOpts = new ArrayList<>();

    public Options(ArrayList<Option> options) {
        if (options == null) {
            return;
        }
        for (Option option : options) {
            addOption(option);
        }
    }

    private void addOption(Option opt) {
        if (opt == null) {
            return;
        }

        String key = opt.getName();

        // add it to the long option list
        if (opt.hasLongOption())
        {
            longOpts.put(opt.getLongName(), opt);
        }

        // if the option is required add it to the required list
        if (opt.isRequired())
        {
            if (requiredOpts.contains(key))
            {
                requiredOpts.remove(opt);
            }
            requiredOpts.add(key);
        }

        shortOpts.put(key, opt);
    }

    public Option getOption(String option) {
        option = Util.stripLeadingHyphens(option);

        if (shortOpts.containsKey(option))
        {
            return shortOpts.get(option);
        }

        return longOpts.get(option);
    }

    public Collection<Option> getOptions() {
        return Collections.unmodifiableCollection(helpOptions());
    }

    private List<Option> helpOptions() {
        return new ArrayList<>(shortOpts.values());
    }
}
