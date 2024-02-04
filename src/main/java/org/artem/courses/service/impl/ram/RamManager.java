package org.artem.courses.service.impl.ram;

import org.artem.courses.entity.Author;
import org.artem.courses.entity.Course;
import org.artem.courses.entity.Topic;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Service
public class RamManager {
    private final Map<Integer, Author> authors = new HashMap<>();
    private final Map<Integer, Course> courses = new HashMap<>();
    private final Map<Integer, Topic> topics = new HashMap<>();

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
