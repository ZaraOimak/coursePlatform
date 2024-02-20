package org.artem.courses.service.impl;

import org.artem.courses.entity.Author;
import org.artem.courses.repository.AuthorRepository;
import org.artem.courses.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
public class DefaultAuthorService implements AuthorService {
    private final AuthorRepository repository;

    public DefaultAuthorService(AuthorRepository repository) {
        this.repository = repository;
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
        if(author.getUuid() == null){
            author.setUuid(UUID.randomUUID());
        } else{
            Author saved = repository.getByUuid(author.getUuid());
            author.setId(saved.getId());
        }
        return repository.save(author);
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
    public List<Author> getAll() {
        return repository.findAll();
    }
}
