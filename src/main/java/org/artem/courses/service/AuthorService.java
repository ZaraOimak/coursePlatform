package org.artem.courses.service;

import org.artem.courses.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AuthorService {
    void delete(Integer id);

    Author update(Author author);

    Author getById(Integer id);

    List<Author> getAll();
}
