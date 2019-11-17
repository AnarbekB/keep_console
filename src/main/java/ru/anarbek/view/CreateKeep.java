package ru.anarbek.view;

import ru.anarbek.cli.Option;

public class CreateKeep extends AbstractView implements View {

    @Override
    public void render(Option option) {
        try {
            String title = option.getChildren().getOption("title").getValue();
            String data = option.getChildren().getOption("data").getValue();

            this.getDriver().output(title + " " + data, true);
        } catch (IndexOutOfBoundsException e) {
            this.getDriver().output("title and data is required", true);
        }
    }
}
