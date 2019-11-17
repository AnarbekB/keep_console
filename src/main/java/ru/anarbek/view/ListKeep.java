package ru.anarbek.view;

public class ListKeep extends AbstractPage implements Page {

    public void render() {
        this.getDriver().output("list", true);
    }
}
