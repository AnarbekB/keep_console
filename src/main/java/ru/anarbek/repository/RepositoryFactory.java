package ru.anarbek.repository;

import ru.anarbek.constant.DataProvider;

public class RepositoryFactory {

    public static Repository build(DataProvider provider) throws RepositoryException {
        switch (provider) {
            case REST:
                return new RESTKeepRepository();
            case DATA_BASE:
                return new DBKeepRepository();
        }

        throw new RepositoryException("Provider not found");
    }

}
