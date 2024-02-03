package org.artem.courses.service;

import org.artem.courses.entity.Topic;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TopicService {
    void delete(Integer id);

    Topic update(Topic topic);

    Topic getById(Integer id);

    List<Topic> getAll();
}
