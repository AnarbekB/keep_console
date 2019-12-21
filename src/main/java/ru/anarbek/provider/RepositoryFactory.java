package ru.anarbek.provider;

public class RepositoryFactory {

    public static DataProvider build(ru.anarbek.constant.DataProvider provider) throws ProviderException {
        switch (provider) {
            case REST:
                return new RESTKeepDataProvider();
            case DATA_BASE:
                return new DBKeepDataProvider();
        }

        throw new ProviderException("Provider not found");
    }

}
