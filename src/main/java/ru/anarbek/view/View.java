package ru.anarbek.view;

import ru.anarbek.cli.Option;
import ru.anarbek.cli.Options;

public interface View {
    void render(Option option, Options options);
}
