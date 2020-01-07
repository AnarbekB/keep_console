package ru.anarbek.view;

import ru.anarbek.cli.Option;
import ru.anarbek.cli.Options;
import ru.anarbek.constant.Argument;
import ru.anarbek.constant.ConsoleColors;
import ru.anarbek.entity.Keep;
import ru.anarbek.provider.ProviderException;
import ru.anarbek.provider.ValidationException;

public class CreateKeep extends AbstractView implements View {

    CreateKeep() throws ProviderException {
        super();
    }

    @Override
    public void render(Option option, Options options) {
        try {
            String title = option.getChildren().getOption(Argument.TITLE_KEEP).getValue();
            String data = option.getChildren().getOption(Argument.CONTENT_KEEP).getValue();

            Keep keep = dataProvider.create(title, data);

            Option silenceMode = option.getChildren().getOption(Argument.SILENCE_CREATE);
            Option colorMode = options.getOption(Argument.COLOR_OUTPUT);
            if (!silenceMode.isOptionPresent()) {
                String color = colorMode.isOptionPresent() ? ConsoleColors.GREEN : null;
                this.outputLn(color + keep.getId() + " | " + keep.getTitle() + " | " + keep.getData());
            }
        } catch (IndexOutOfBoundsException|ValidationException e) {
            this.outputLn("Validate error: title and data is required");
        } catch (ProviderException e) {
            this.outputLn(e.getMessage());
        }
    }
}
