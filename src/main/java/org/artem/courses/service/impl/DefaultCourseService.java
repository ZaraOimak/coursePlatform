package org.artem.courses.service.impl;

import org.artem.courses.dto.CourseDTO;
import org.artem.courses.entity.Course;
import org.artem.courses.mapper.CourseMapper;
import org.artem.courses.repository.CourseRepository;
import org.artem.courses.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultCourseService implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public DefaultCourseService(CourseRepository repository, CourseMapper courseMapper) {
        this.courseRepository = repository;
        this.courseMapper = courseMapper;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Course course = courseRepository.getReferenceById(id);
        course.getAuthor().getCourses().remove(course);
        courseRepository.delete(course);
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        Course course = courseRepository.getByUuid(uuid);
        if (course != null) {
            course.getAuthor().getCourses().remove(course);
            courseRepository.delete(course);
        }
    }

    @Override
    @Transactional
    public Course update(Course course) {
        if (course.getUuid() == null) {
            course.setUuid(UUID.randomUUID());
        } else {
            Course saved = courseRepository.getByUuid(course.getUuid());
            if (saved != null) {
                course.setId(saved.getId());
            }
        }
        return courseRepository.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public Course getById(Integer id) {
        return courseRepository.getReferenceById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Course getByUuid(UUID uuid) {
        return courseRepository.getByUuid(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public CourseDTO update(CourseDTO courseDTO) {
        Course course = getCourse(courseDTO.getUuid());
        courseMapper.updateCourseFromDto(courseDTO, course);
        courseRepository.save(course);
        return courseMapper.toDto(course);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDTO getByUuidDto(UUID uuid) {
        return courseMapper.toDto(getByUuid(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getAllDto() {
        return courseMapper.toDtoList(getAll());
    }

    private Course getCourse(UUID uuid) {
        Course course = courseRepository.getByUuid(uuid);
        if (course == null) {
            course = new Course();
            course.setUuid(UUID.randomUUID());
        }
        return course;
    }
}
