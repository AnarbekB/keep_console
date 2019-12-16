package ru.anarbek.view;

import ru.anarbek.constant.DataProvider;
import ru.anarbek.repository.Repository;
import ru.anarbek.repository.RepositoryException;
import ru.anarbek.repository.RepositoryFactory;

abstract public class AbstractView {

    protected Repository repository;

    AbstractView() throws RepositoryException {
        repository = RepositoryFactory.build(DataProvider.DATA_BASE);
    }

    protected void outputLn(String output) {
        System.out.println(output);
    }
}
