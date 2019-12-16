package ru.anarbek.view;

import ru.anarbek.cli.Option;
import ru.anarbek.repository.RepositoryException;

public class CreateKeep extends AbstractView implements View {

    CreateKeep() throws RepositoryException {
        super();
    }

    @Override
    public void render(Option option) {
        try {
            String title = option.getChildren().getOption("title").getValue();
            String data = option.getChildren().getOption("data").getValue();

            this.outputLn(title + " " + data);
        } catch (IndexOutOfBoundsException e) {
            this.outputLn("title and data is required");
        }
    }
}
