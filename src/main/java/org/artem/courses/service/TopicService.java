package org.artem.courses.service;

import org.artem.courses.entity.Topic;

import java.util.List;

public interface TopicService {
    void delete(Integer id);

    Topic update(Topic topic);

    Topic getById(Integer id);

    List<Topic> getAll();
}
