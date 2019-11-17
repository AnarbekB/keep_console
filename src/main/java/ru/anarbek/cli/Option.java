package ru.anarbek.cli;

import ru.anarbek.constant.Argument;

public class Option {

    private final String name;

    private String longName;

    private boolean required;

    private Argument argument;

    private String description;

    public Option(String name, String longName, boolean required, String description, Argument argument) {
        this.name = name;
        this.longName = longName;
        this.required = required;
        this.description = description;
        this.argument = argument;
    }

    public Argument getArgument() {
        return argument;
    }

    public String getName()
    {
        // if 'option' is null, then it is a 'long' option
        return (name == null) ? longName : name;
    }

    public boolean hasLongOption()
    {
        return longName != null;
    }

    public String getLongName() {
        return longName;
    }

    public boolean isRequired() {
        return required;
    }

    public String getDescription() {
        return description;
    }

    public String getNameWithHyphens() {
        return "-".concat(this.name);
    }

    public String getLongNameWithHyphens() {
        return "--".concat(this.longName);
    }
}
