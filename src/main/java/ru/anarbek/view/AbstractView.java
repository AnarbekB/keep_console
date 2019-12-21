package ru.anarbek.view;

import ru.anarbek.provider.DataProvider;
import ru.anarbek.provider.ProviderException;
import ru.anarbek.provider.RepositoryFactory;

abstract public class AbstractView {

    protected DataProvider dataProvider;

    AbstractView() throws ProviderException {
        dataProvider = RepositoryFactory.build(ru.anarbek.constant.DataProvider.DATA_BASE);
    }

    protected void outputLn(String output) {
        System.out.println(output);
    }
}
