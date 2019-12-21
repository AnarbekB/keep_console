package ru.anarbek.provider;

import ru.anarbek.entity.Keep;

import java.util.List;

public interface DataProvider {
    List<Keep> getAll() throws ProviderException;

    Keep create(String title, String $data) throws ValidationException, ProviderException;
}
