package org.artem.courses.service;

import org.artem.courses.dto.AuthorDTO;
import org.artem.courses.entity.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    void delete(Integer id);

    void delete(UUID uuid);

    Author update(Author author);

    AuthorDTO update(AuthorDTO authorDTO);

    Author getById(Integer id);

    Author getByUuid(UUID uuid);

    AuthorDTO getByUuidDto(UUID uuid);

    List<Author> getAll();

    List<AuthorDTO> getAllDto();

}
