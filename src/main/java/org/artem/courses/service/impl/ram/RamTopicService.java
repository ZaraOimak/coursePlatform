package org.artem.courses.service.impl.ram;

import org.artem.courses.entity.Topic;
import org.artem.courses.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RamTopicService implements TopicService {
    private final RamManager ramManager;

    public RamTopicService(RamManager ramManager) {
        this.ramManager = ramManager;
    }

    @Override
    public void delete(Integer id) {
        if (id != null) {
            ramManager.getTopics().remove(id);
        }
    }
    @Override
    public void delete(UUID uuid) {
        if (uuid != null) {
            ramManager.getTopicsByUuid().remove(uuid);

        }
    }

    @Override
    public Topic update(Topic topic) {
        if (topic.getUuid() == null && topic.getId() == null) {
            topic.setUuid(UUID.randomUUID());
            topic.setId(ramManager.getNewTopicId());
        }else{
            Topic saved = ramManager.getTopicsByUuid().get(topic.getUuid());
            topic.setId(saved.getId());
        }
        ramManager.getTopics().put(topic.getId(), topic);
        ramManager.getTopicsByUuid().put(topic.getUuid(), topic);
        return topic;
    }

    @Override
    public Topic getById(Integer id) {
        return ramManager.getTopics().get(id);
    }

    @Override
    public Topic getByUuid(UUID uuid) {
        return ramManager.getTopicsByUuid().get(uuid);
    }

    @Override
    public List<Topic> getAll() {
        return new ArrayList<>(ramManager.getTopics().values());
    }

    @Override
    public List<Topic> getAll(Integer courseId) {
        List<Topic> topics = new ArrayList<>();
        if (courseId == null) {
            return topics;
        }
        for (Topic topic : ramManager.getTopics().values()) {
            if (courseId.equals(topic.getSection().getCourse().getId())) {
                topics.add(topic);
            }
        }
        return topics;
    }

    @Override
    public List<Topic> getAll(UUID courseUuid) {
        List<Topic> topics = new ArrayList<>();
        if (courseUuid == null) {
            return topics;
        }
        for (Topic topic : ramManager.getTopicsByUuid().values()) {
            if (courseUuid.equals(topic.getSection().getCourse().getUuid())) {
                topics.add(topic);
            }
        }
    return topics;
    }
}
