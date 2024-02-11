package org.artem.courses.service;

import org.artem.courses.entity.Topic;

import java.util.List;
import java.util.UUID;

public interface TopicService {
    void delete(Integer id);
    void delete(UUID uuid);

    Topic update(Topic topic);

    Topic getById(Integer id);
    Topic getByUuid(UUID uuid);

    List<Topic> getAll();

    List<Topic> getAll(Integer courseId);
    List<Topic> getAll(UUID courseUuid);
}
