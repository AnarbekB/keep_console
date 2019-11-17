package ru.anarbek.view;

public class CreateKeep extends AbstractView implements View {

    public void render() {
        this.getDriver().output("created", true);
    }
}
