package ru.anarbek.view;

public class MainMenu extends AbstractPage implements Page {
    public void render() {
        this.getDriver().output("1. hello", true);
    }
}
