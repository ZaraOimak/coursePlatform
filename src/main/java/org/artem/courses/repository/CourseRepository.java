package org.artem.courses.repository;

import org.artem.courses.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    void deleteByUuid(UUID uuid);

    Course getByUuid(UUID uuid);
}
