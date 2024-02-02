package org.artem.courses.service.impl.ram;

import org.artem.courses.entity.Author;
import org.artem.courses.service.AuthorService;

import java.util.ArrayList;
import java.util.List;

public class RamAuthorService implements AuthorService {
    private final RamManager ramManager;

    public RamAuthorService(RamManager ramManager) {
        this.ramManager = ramManager;
    }

    @Override
    public void delete(Integer id) {
        if(id != null && ramManager.getAuthors().containsKey(id)){
            Author author = ramManager.getAuthors().get(id);
            if(author.getCourses() == null || author.getCourses().isEmpty()){
                ramManager.getAuthors().remove(author.getId());
            }
        }
    }
    @Override
    public Author update(Author author) {
        if (author.getId() == null) {
            author.setId(ramManager.getNewAuthorId());
        }
        ramManager.getAuthors().put(author.getId(), author);
        return author;
    }

    @Override
    public Author getById(Integer id) {
        return ramManager.getAuthors().get(id);
    }

    @Override
    public List<Author> getAll() {
        return new ArrayList<>(ramManager.getAuthors().values());
    }
}
