package ru.anarbek.view;

import ru.anarbek.cli.Option;

public class ListKeep extends AbstractView implements View {

    public void render(Option option) {
        this.getDriver().output("list", true);
    }
}
