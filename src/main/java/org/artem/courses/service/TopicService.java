package org.artem.courses.service;

import org.artem.courses.entity.Topic;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TopicService {
    void delete(Integer id);

    Topic update(Topic topic);
    Topic create(Integer courseId,Topic topic);

    Topic getById(Integer id);

    List<Topic> getAll();
    List<Topic> getAll(Integer courseId);

}
