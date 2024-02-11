package org.artem.courses.service.impl.ram;

import org.artem.courses.entity.Course;
import org.artem.courses.entity.Section;
import org.artem.courses.entity.Topic;
import org.artem.courses.service.CourseService;
import org.artem.courses.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RamCourseService implements CourseService {
    private final RamManager ramManager;
    private final TopicService topicService;

    public RamCourseService(RamManager ramManager, TopicService topicService) {
        this.ramManager = ramManager;
        this.topicService = topicService;
    }

    @Override
    public void delete(Integer id) {
        if (id != null && ramManager.getCourses().containsKey(id)) {
            Course course = ramManager.getCourses().remove(id);
            for (Section section : course.getSections()) {
                for (Topic topic : section.getTopics()) {
                    topicService.delete(topic.getId());
                }
            }
        }
    }

    @Override
    public void delete(UUID uuid) {
        if (uuid != null && ramManager.getCoursesByUuid().containsKey(uuid)) {
            Course course = ramManager.getCoursesByUuid().remove(uuid);
            for (Section section : course.getSections()) {
                for (Topic topic : section.getTopics()) {
                    topicService.delete(topic.getUuid());
                }
            }
        }
    }

    @Override
    public Course update(Course course) {
        if (course.getId() == null && course.getUuid() == null) {
            course.setId(ramManager.getNewCourseId());
            course.setUuid(UUID.randomUUID());
        } else{
            Course oldCourse = ramManager.getCoursesByUuid().get(course.getUuid());
            course.setId(oldCourse.getId());
        }
        for(Section section : course.getSections()){
            section.setCourse(course);
        }
        ramManager.getCourses().put(course.getId(), course);
        ramManager.getCoursesByUuid().put(course.getUuid(), course);
        return course;
    }

    @Override
    public Course getById(Integer id) {
        return ramManager.getCourses().get(id);
    }

    @Override
    public Course getByUuid(UUID uuid) {
        return ramManager.getCoursesByUuid().get(uuid);
    }

    @Override
    public List<Course> getAll() {
        return new ArrayList<>(ramManager.getCourses().values());
    }
}
