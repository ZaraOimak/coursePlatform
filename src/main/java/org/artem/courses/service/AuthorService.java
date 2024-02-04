package org.artem.courses.service;

import org.artem.courses.entity.Author;


import java.util.List;

public interface AuthorService {
    void delete(Integer id);

    Author update(Author author);

    Author getById(Integer id);

    List<Author> getAll();
}
