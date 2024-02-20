package org.artem.courses.repository;

import org.artem.courses.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    void deleteByUuid(UUID uuid);
    Author getByUuid(UUID uuid);
}

