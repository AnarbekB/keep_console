package ru.anarbek.repository;

import ru.anarbek.entity.Keep;

import java.util.List;

public interface Repository {
    List<Keep> getAll() throws RepositoryException;
}
