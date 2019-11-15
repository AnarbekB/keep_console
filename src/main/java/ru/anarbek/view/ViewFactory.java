package ru.anarbek.view;

import ru.anarbek.constant.Argument;

public class ViewFactory {

    public static Page build(Argument argument) throws Exception {
        switch (argument) {
            case CREATE_KEEP:
                return new CreateKeep();
            case HELP:
                return new Help();
        }

        throw new Exception("page not found");
    }
}
