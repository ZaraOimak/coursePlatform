package org.artem.courses.service.impl;

import org.artem.courses.dto.AuthorDTO;
import org.artem.courses.entity.Author;
import org.artem.courses.mapper.AuthorMapper;
import org.artem.courses.repository.AuthorRepository;
import org.artem.courses.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultAuthorService implements AuthorService {
    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    public DefaultAuthorService(AuthorRepository repository, AuthorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        repository.deleteByUuid(uuid);
    }

    @Override
    @Transactional
    public Author update(Author author) {
        if (author.getUuid() == null) {
            author.setUuid(UUID.randomUUID());
        } else {
            Author saved = repository.getByUuid(author.getUuid());
            if (saved != null) {
                author.setId(saved.getId());
            }
        }
        return repository.save(author);
    }

    @Override
    public AuthorDTO update(AuthorDTO authorDTO) {
        Author author = getAuthor(authorDTO.getUuid());
        mapper.updateAuthorFromDto(authorDTO, author);
        repository.save(author);
        return mapper.toDto(author);
    }

    private Author getAuthor(UUID authorUuid) {
        Author author = repository.getByUuid(authorUuid);
        if (author == null) {
            author = new Author();
            author.setUuid(UUID.randomUUID());
        }
        return author;
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(Integer id) {
        return repository.getReferenceById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getByUuid(UUID uuid) {
        return repository.getByUuid(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDTO getByUuidDto(UUID uuid) {
        return mapper.toDto(getByUuid(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDTO> getAllDto() {
        return mapper.toDtoList(getAll());
    }
}
