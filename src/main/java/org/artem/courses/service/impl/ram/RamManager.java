package org.artem.courses.service.impl.ram;

import org.artem.courses.entity.Author;
import org.artem.courses.entity.Course;
import org.artem.courses.entity.Topic;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RamManager {
    private final Map<Integer, Author> authors = new HashMap<>();
    private final Map<Integer, Course> courses = new HashMap<>();
    private final Map<Integer, Topic> topics = new HashMap<>();
     private final Map<UUID, Author> authorsByUuid = new HashMap<>();

    public Map<UUID, Author> getAuthorsByUuid() {
        return authorsByUuid;
    }

    public Map<UUID, Course> getCoursesByUuid() {
        return coursesByUuid;
    }

    public Map<UUID, Topic> getTopicsByUuid() {
        return topicsByUuid;
    }

    private final Map<UUID, Course> coursesByUuid  = new HashMap<>();
    private final Map<UUID, Topic> topicsByUuid  = new HashMap<>();


    public Map<Integer, Author> getAuthors() {
        return authors;
    }

    public Map<Integer, Course> getCourses() {
        return courses;
    }

    public Map<Integer, Topic> getTopics() {
        return topics;
    }
    public Integer getNewAuthorId(){
        if(authors.isEmpty()){
            return 0;
        }
        return Collections.max(authors.keySet())+1;
    } public Integer getNewCourseId(){
        if(courses.isEmpty()){
            return 0;
        }
        return Collections.max(courses.keySet())+1;
    } public Integer getNewTopicId(){
        if(topics.isEmpty()){
            return 0;
        }
        return Collections.max(topics.keySet())+1;
    }
}
