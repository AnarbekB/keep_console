package ru.anarbek.view;

public class Help extends AbstractPage implements Page {

    public void render() {
        this.getDriver().output("help", true);
    }
}
