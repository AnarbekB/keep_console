package ru.anarbek.cli;

import ru.anarbek.constant.Argument;

import java.util.ArrayList;
import java.util.List;

public class Option {

    private final int id;

    private final String name;

    private String longName;

    private boolean required;

    private Argument argument;

    private String description;

    private Option parent;

    private Options children;

    private boolean hasData;

    private List<String> values = new ArrayList<>();

    private char valueSeparator;

    public Option(int id, String name, String longName, boolean required, boolean hasData, String description, Argument argument, Option parent) {
        this.id = id;
        this.name = name;
        this.longName = longName;
        this.required = required;
        this.description = description;
        this.argument = argument;
        this.parent = parent;
        this.hasData = hasData;
        this.valueSeparator = 0;
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

    public boolean hasParent() {
        return parent != null;
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

    public boolean hasChildren() {
        return children != null;
    }

    public Options getChildren() {
        return children;
    }

    public boolean isHasData() {
        return hasData;
    }

    public void addValue(String value) {
        values.add(value);
    }

    public String getValue() throws IndexOutOfBoundsException
    {
        return hasData ? values.get(0) : null ;
    }

    public String getValue(int index) throws IndexOutOfBoundsException
    {
        return hasData ? null : values.get(index);
    }
}
