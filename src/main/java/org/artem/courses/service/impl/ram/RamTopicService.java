package org.artem.courses.service.impl.ram;

import org.artem.courses.entity.Topic;
import org.artem.courses.service.TopicService;

import java.util.ArrayList;
import java.util.List;

public class RamTopicService implements TopicService {
    private final RamManager ramManager;
    public RamTopicService(RamManager ramManager) {
        this.ramManager = ramManager;
    }
    @Override
    public void delete(Integer id) {
        if(id != null){
            ramManager.getTopics().remove(id);
        }
    }
    @Override
    public Topic update(Topic topic) {
        if(topic.getId() == null){
            topic.setId(ramManager.getNewTopicId());
        }
        ramManager.getTopics().put(topic.getId(),topic);
        return topic;
    }

    @Override
    public Topic getById(Integer id) {
        return ramManager.getTopics().get(id);
    }

    @Override
    public List<Topic> getAll() {
        return new ArrayList<>(ramManager.getTopics().values());
    }
}
