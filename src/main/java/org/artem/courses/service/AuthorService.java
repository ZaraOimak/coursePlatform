package org.artem.courses.service;

import org.artem.courses.entity.Author;


import java.util.List;
import java.util.UUID;

public interface AuthorService {
    void delete(Integer id);
    void delete(UUID uuid);

    Author update(Author author);

    Author getById(Integer id);
    Author getByUuid(UUID uuid);

    List<Author> getAll();
}
