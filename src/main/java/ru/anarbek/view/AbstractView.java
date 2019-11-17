package ru.anarbek.view;

import ru.anarbek.interaction.DriverConsole;
import ru.anarbek.interaction.Interaction;

abstract public class AbstractView {
    private Interaction driver;

    public AbstractView() {
        this.driver = new DriverConsole();
    }

    protected Interaction getDriver() {
        return driver;
    }
}
