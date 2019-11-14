package ru.anarbek.view;

public class CreateKeep extends AbstractPage implements Page {

    public void render() {
        this.getDriver().output("created", true);
    }
}
