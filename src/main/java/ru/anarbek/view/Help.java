package ru.anarbek.view;

public class Help extends AbstractView implements View {

    public void render() {
        this.getDriver().output("help", true);
    }
}
