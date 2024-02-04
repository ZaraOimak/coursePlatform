package org.artem.courses.service.impl.ram;

import org.artem.courses.entity.Author;
import org.artem.courses.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RamAuthorService implements AuthorService {
    private final RamManager ramManager;

    public RamAuthorService(RamManager ramManager) {
        this.ramManager = ramManager;
    }

    @Override
    public void delete(Integer id) {
        if (id != null && ramManager.getAuthors().containsKey(id)) {
            Author author = ramManager.getAuthors().get(id);
            if (author.getCourses() == null || author.getCourses().isEmpty()) {
                ramManager.getAuthorsByUuid().remove(author.getUuid());
                ramManager.getAuthors().remove(author.getId());
            }
        }
    }

    @Override
    public void delete(UUID uuid) {
        if (uuid != null && ramManager.getAuthorsByUuid().containsKey(uuid)) {
            Author author = ramManager.getAuthorsByUuid().get(uuid);
            if (author.getCourses() == null || author.getCourses().isEmpty()) {
                ramManager.getAuthorsByUuid().remove(author.getUuid());
                ramManager.getAuthors().remove(author.getId());
            }
        }
    }

    @Override
    public Author update(Author author) {
        if (author.getUuid() == null && author.getId() == null) {
            author.setUuid(UUID.randomUUID());
            author.setId(ramManager.getNewAuthorId());
        }else{
            Author saved = ramManager.getAuthorsByUuid().get(author.getUuid());
            author.setId(saved.getId());
        }
        ramManager.getAuthors().put(author.getId(), author);
        ramManager.getAuthorsByUuid().put(author.getUuid(), author);
        return author;
    }

    @Override
    public Author getById(Integer id) {
        return ramManager.getAuthors().get(id);
    }

    @Override
    public Author getByUuid(UUID uuid) {
        return ramManager.getAuthorsByUuid().get(uuid);
    }

    @Override
    public List<Author> getAll() {
        return new ArrayList<>(ramManager.getAuthors().values());
    }
}
