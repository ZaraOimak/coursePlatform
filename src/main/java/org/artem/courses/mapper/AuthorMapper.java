package org.artem.courses.mapper;

import org.artem.courses.dto.AuthorDTO;
import org.artem.courses.entity.Author;
import org.artem.courses.entity.Course;
import org.artem.courses.service.CourseService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class AuthorMapper {
    @Autowired
    private CourseService courseService;

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "courses", source = "coursesUuids")
    public abstract void updateAuthorFromDto(AuthorDTO dto, @MappingTarget Author entity);

    @Mapping(target = "coursesUuids", source = "courses")
    public abstract AuthorDTO toDto(Author entity);

    public abstract List<AuthorDTO> toDtoList(List<Author> authors);

    protected List<Course> coursesUuidsToCourses(List<UUID> coursesUuids) {
        if (coursesUuids == null) {
            return null;
        }
        return coursesUuids.stream().map(courseService::getByUuid).collect(Collectors.toList());
    }

    protected List<UUID> coursesToCoursesUuids(List<Course> courses) {
        if (courses == null) {
            return null;
        }
        return courses.stream().map(Course::getUuid).collect(Collectors.toList());
    }

}






