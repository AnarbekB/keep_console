package ru.anarbek.provider;

import ru.anarbek.entity.Keep;

import java.util.List;

public class RESTKeepDataProvider implements DataProvider {

    @Override
    public List<Keep> getAll() throws ProviderException {
        return null;
    }

    @Override
    public Keep create(String title, String $data) throws ValidationException, ProviderException {
        return null;
    }
}
