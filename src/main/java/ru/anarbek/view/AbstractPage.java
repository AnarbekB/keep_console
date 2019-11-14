package ru.anarbek.view;

import ru.anarbek.interaction.DriverConsole;
import ru.anarbek.interaction.Interaction;

abstract public class AbstractPage {
    private Interaction driver;

    public AbstractPage() {
        this.driver = new DriverConsole();
    }

    protected Interaction getDriver() {
        return driver;
    }
}
