package ru.anarbek.view;

public class ListKeep extends AbstractView implements View {

    public void render() {
        this.getDriver().output("list", true);
    }
}
