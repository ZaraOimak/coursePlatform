package org.artem.courses.controller;

import ch.qos.logback.core.CoreConstants;
import org.artem.courses.dto.CourseDTO;
import org.artem.courses.dto.SectionDTO;
import org.artem.courses.entity.Course;
import org.artem.courses.entity.Section;
import org.artem.courses.entity.Topic;
import org.artem.courses.service.AuthorService;
import org.artem.courses.service.CourseService;
import org.artem.courses.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")

public class CourseController {
    private final CourseService courseService;
    private final TopicService topicService;

    private final AuthorService authorService;

    @Autowired
    public CourseController(CourseService courseService, TopicService topicService, AuthorService authorService) {
        this.courseService = courseService;
        this.topicService = topicService;
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<Course> courses = courseService.getAll();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses){
            courseDTOS.add(transform(course));
        }
        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") int id) {
        Course course = courseService.getById(id);
        if (course != null) {
            return new ResponseEntity<>(transform(course), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        Course createdCourse = courseService.update(transform(courseDTO));
        return new ResponseEntity<>(transform(createdCourse), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") int id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    private CourseDTO transform (Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setAuthorUuid(course.getAuthor().getUuid());

        List <SectionDTO> sections = new ArrayList<>();
        for(Section section : course.getSections()){
            sections.add(transform(section));
        }
        courseDTO.setSections(sections);
        return courseDTO;
    }

    private SectionDTO transform(Section section) {
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(section.getId());
        sectionDTO.setName(section.getName());
        sectionDTO.setOrder(section.getOrder());
        sectionDTO.setDescription(section.getDescription());

        List<Integer> topicsIds = new ArrayList<>();
        for(Topic topic : section.getTopics()){
            topicsIds.add(topic.getId());
        }
        sectionDTO.setTopicsIds(topicsIds);
        return sectionDTO;
    }
    private Course transform(CourseDTO courseDTO){
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());

        course.setAuthor(authorService.getByUuid(courseDTO.getAuthorUuid()));
        List<Section> sections = new ArrayList<>();
        for(SectionDTO sectionDTO : courseDTO.getSections()){
            sections.add(transform(sectionDTO,course));
        }
        course.setSections(sections);
        return course;
    }

    private Section transform(SectionDTO sectionDTO, Course course) {
        Section section = new Section();
        section.setId(sectionDTO.getId());
        section.setName(sectionDTO.getName());
        section.setOrder(sectionDTO.getOrder());
        section.setDescription(sectionDTO.getDescription());
        section.setCourse(course);
        List<Topic> topics = new ArrayList<>();
        for(Integer topicId : sectionDTO.getTopicsIds()){
            topics.add(topicService.getById(topicId));
        }
        section.setTopics(topics);
        return section;
    }
}
