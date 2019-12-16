package ru.anarbek.view;

import ru.anarbek.cli.LoadFileException;
import ru.anarbek.cli.Option;
import ru.anarbek.cli.Options;
import ru.anarbek.cli.OptionsBuilder;

public class Help extends AbstractView implements View {

    public void render(Option option) {
        try {
            Options options = OptionsBuilder.build();
            this.outputLn(this.getContent(options));
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
