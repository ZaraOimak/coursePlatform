package org.artem.courses.service.impl.ram;

import org.artem.courses.entity.Author;
import org.artem.courses.service.AuthorService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
public class RamAuthorServiceTest {
    @Test
    void shouldCreateNewAuthor() {
        // given
        RamManager ramManager = new RamManager();
        AuthorService authorService = new RamAuthorService(ramManager);
        Author author = new Author();
        author.setName("Тест автор");
        assertThat(author.getId()).isNull();

        // when
        Author updatedAuthor = authorService.update(author);

        // then
        assertThat(updatedAuthor.getName()).isEqualTo(author.getName());
        assertThat(updatedAuthor.getId()).isEqualTo(0);
        assertThat(author.getId()).isEqualTo(0);
        assertThat(updatedAuthor).isEqualTo(author);
        assertThat(ramManager.getAuthors()).hasSize(1).contains(Map.entry(0, author));
    }

    @Test
    void shouldUpdateAuthor() {
        // given
        RamManager ramManager = new RamManager();
        AuthorService authorService = new RamAuthorService(ramManager);
        Author old = new Author();
        old.setName("Тест автор");
        old.setId(5);
        old.setUuid(UUID.randomUUID());
        ramManager.getAuthors().put(5, old);
        ramManager.getAuthorsByUuid().put(old.getUuid(), old);
        Author updatedAuthor = new Author();
        updatedAuthor.setName("Новое имя");
        updatedAuthor.setId(5);
        updatedAuthor.setUuid(old.getUuid());

        // when
        Author resultAuthor = authorService.update(updatedAuthor);

        // then
        assertThat(resultAuthor.getName()).isEqualTo(updatedAuthor.getName());
        assertThat(resultAuthor.getId()).isEqualTo(updatedAuthor.getId());
        assertThat(updatedAuthor.getId()).isEqualTo(5);
        assertThat(resultAuthor).isEqualTo(updatedAuthor);
        assertThat(ramManager.getAuthors()).hasSize(1).contains(Map.entry(5, updatedAuthor));
    }

    @Test
    void shouldDeleteAuthorWithNoCourses() {
        // given
        RamManager ramManager = new RamManager();
        AuthorService authorService = new RamAuthorService(ramManager);
        Author author = new Author();
        author.setName("Тест автор");
        authorService.update(author);
        int authorId = author.getId();

        // when
        authorService.delete(authorId);

        // then
        assertThat(ramManager.getAuthors()).hasSize(0);
    }

    @Test
    void shouldReturnNullWhenThereIsNoSuchAuthor() {
        // given
        RamManager ramManager = new RamManager();
        AuthorService authorService = new RamAuthorService(ramManager);

        // when
        Author author = authorService.getById(999);

        // then
        assertThat(author).isNull();
    }

    @Test
    void shouldReturnAllAuthors() {
        // given
        RamManager ramManager = new RamManager();
        AuthorService authorService = new RamAuthorService(ramManager);

        Author author1 = new Author();
        author1.setName("Тест автор 1");
        Author author2 = new Author();
        author2.setName("Тест автор 2");

        authorService.update(author1);
        authorService.update(author2);

        // when
        List<Author> allAuthors = authorService.getAll();

        // then
        assertThat(allAuthors).hasSize(2);
    }
}
