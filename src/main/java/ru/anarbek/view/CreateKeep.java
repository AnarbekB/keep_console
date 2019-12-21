package ru.anarbek.view;

import ru.anarbek.cli.Option;
import ru.anarbek.entity.Keep;
import ru.anarbek.provider.ProviderException;
import ru.anarbek.provider.ValidationException;

public class CreateKeep extends AbstractView implements View {

    CreateKeep() throws ProviderException {
        super();
    }

    @Override
    public void render(Option option) {
        try {
            String title = option.getChildren().getOption("title").getValue();
            String data = option.getChildren().getOption("data").getValue();

            Keep keep = dataProvider.create(title, data);
            this.outputLn(keep.getId() + " | " + keep.getTitle() + " | " + keep.getData());
        } catch (IndexOutOfBoundsException|ValidationException e) {
            this.outputLn("Validate error: title and data is required");
        } catch (ProviderException e) {
            this.outputLn(e.getMessage());
        }
    }
}
