package org.artem.courses.service;

import org.artem.courses.entity.Course;

import java.util.List;

public interface CourseService {
    void delete(Integer id);
    Course update(Course course);
    Course getById(Integer id);
    List<Course> getAll();
}
