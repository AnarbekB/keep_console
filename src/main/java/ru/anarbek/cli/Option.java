package ru.anarbek.cli;

import ru.anarbek.constant.Argument;

public class Option {

    private final int id;

    private final String name;

    private String longName;

    private boolean required;

    private Argument argument;

    private String description;

    private Option parent;

    private Options children;

    public Option(int id, String name, String longName, boolean required, String description, Argument argument, Option parent) {
        this.id = id;
        this.name = name;
        this.longName = longName;
        this.required = required;
        this.description = description;
        this.argument = argument;
        this.parent = parent;
    }

    public int getId() {
        return id;
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

    public Option getParent() {
        return parent;
    }

    public void addChildren(Option option) {
        if (children == null) {
            this.children = new Options(option);
        } else {
            this.children.addOption(option);
        }
    }
}
