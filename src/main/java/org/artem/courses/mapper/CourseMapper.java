package org.artem.courses.mapper;

import org.artem.courses.dto.CourseDTO;
import org.artem.courses.dto.SectionDTO;
import org.artem.courses.entity.Author;
import org.artem.courses.entity.Course;
import org.artem.courses.entity.Section;
import org.artem.courses.repository.AuthorRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = SectionMapper.class)
public abstract class CourseMapper {
    @Autowired
    private AuthorRepository authorRepository;

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "author", expression = "java(uuidToAuthor(dto.getAuthorUuid(), entity))")
    @Mapping(target = "sections", expression = "java(updateSections(dto,entity))")
    public abstract void updateCourseFromDto(CourseDTO dto, @MappingTarget Course entity);

    @Mapping(target = "authorUuid", source = "author")
    public abstract CourseDTO toDto(Course entity);

    public abstract List<CourseDTO> toDtoList(List<Course> courses);

//    protected Author mapUuidToAuthor(UUID value) {
//        if (value == null) {
//            return null;
//        }
//        return authorRepository.getByUuid(value);
//    }
//
//    protected UUID mapAuthorToUuid(Author author) {
//        if (author == null) {
//            return null;
//        }
//        return author.getUuid();
//    }

    protected List<Section> updateSections(CourseDTO courseDTO, @MappingTarget Course entity) {
        SectionMapper sectionMapper = Mappers.getMapper(SectionMapper.class);
        if (courseDTO.getSections() == null) {
            return entity.getSections();
        }
        List<Section> sections = entity.getSections();
        if (courseDTO.getSections().isEmpty()) {
            sections.clear();
        } else {
            List<UUID> uuids = courseDTO.getSections().stream().map(SectionDTO::getUuid).toList();
            sections.removeIf(section -> !uuids.contains(section.getUuid()));

            for (SectionDTO sectionDTO : courseDTO.getSections()) {
                Section section = getOrCreateSection(entity, sectionDTO.getUuid());
                sectionMapper.updateSectionFromDto(sectionDTO, section);
                if (section.getId() == null) {
                    sections.add(section);
                }
            }
        }
        return sections;
    }

    private Section getOrCreateSection(Course course, UUID sectionUuid) {
        if (sectionUuid == null) {
            Section section = new Section();
            section.setUuid(UUID.randomUUID());
            section.setCourse(course);
            section.setPosition(course.getSections().stream().mapToInt(Section::getPosition).max().orElse(-1) + 1);
            return section;
        }
        return course.getSections().stream().filter(section -> section.getUuid().equals(sectionUuid)).findFirst().orElseThrow();
    }

    protected Author uuidToAuthor(UUID authorUuid, Course entity) {
        if (authorUuid == null) {
            return entity.getAuthor();
        }
        Author author = authorRepository.getByUuid(authorUuid);
        author.getCourses().remove(entity);
        author.getCourses().add(entity);
        return author;
    }

    protected UUID authorToUuid(Author author) {
        if (author == null) {
            return null;
        }
        return author.getUuid();
    }

}
