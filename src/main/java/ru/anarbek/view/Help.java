package ru.anarbek.view;

import ru.anarbek.cli.LoadFileException;
import ru.anarbek.cli.Option;
import ru.anarbek.cli.Options;
import ru.anarbek.cli.OptionsBuilder;
import ru.anarbek.provider.ProviderException;

public class Help extends AbstractView implements View {

    Help() throws ProviderException {
        super();
    }

    public void render(Option option, Options options) {
        try {
            Options helpOptions = OptionsBuilder.build();
            this.outputLn(this.getContent(helpOptions));
        } catch (LoadFileException e) {
            this.outputLn("Error with load file");
        }
    }

    /**
     * see resources/options.xml
     */
    private String getContent(Options options) {
        StringBuilder stringBuilder = new StringBuilder("Help: \n");

        for (Option option : options.getOptions()) {
            stringBuilder.append(option.getNameWithHyphens());
            stringBuilder.append(" | ");
            stringBuilder.append(option.getLongNameWithHyphens());
            stringBuilder.append(" : ");
            stringBuilder.append(option.getDescription());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
