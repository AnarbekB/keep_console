package ru.anarbek.view;

import ru.anarbek.constant.Argument;

public class ViewFactory {

    public static Page buid(Argument argument) throws Exception {
        switch (argument) {
            case MAIN:
                return new MainMenu();
            case CREATE_KEEP:
                return new CreateKeep();
            case HELP:
                return new Help();
        }

        throw new Exception("page not found");
    }
}
