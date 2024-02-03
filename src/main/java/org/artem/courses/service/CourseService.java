package org.artem.courses.service;

import org.artem.courses.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CourseService {
    void delete(Integer id);
    Course update(Course course);
    Course getById(Integer id);
    List<Course> getAll();
}
