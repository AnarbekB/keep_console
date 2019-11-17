package ru.anarbek.view;

import ru.anarbek.cli.Option;
import ru.anarbek.cli.Options;
import ru.anarbek.cli.OptionsBuilder;

public class Help extends AbstractView implements View {

    public void render() {
        this.getDriver().output(this.getContent(), true);
    }

    /**
     * see resources/options.xml
     */
    private String getContent() {
        Options options = OptionsBuilder.build();
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
