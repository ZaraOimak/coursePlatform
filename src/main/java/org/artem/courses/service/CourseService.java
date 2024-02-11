package org.artem.courses.service;

import org.artem.courses.entity.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    void delete(Integer id);

    void delete(UUID uuid);

    Course update(Course course);

    Course getById(Integer id);

    Course getByUuid(UUID uuid);

    List<Course> getAll();
}
