package org.artem.courses.service.impl.ram;

import org.artem.courses.entity.Course;
import org.artem.courses.entity.Section;
import org.artem.courses.entity.Topic;
import org.artem.courses.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Topic update(Topic topic) {
        if (topic.getId() == null) {
            throw new IllegalArgumentException();
        }
        ramManager.getTopics().put(topic.getId(), topic);
        return topic;
    }

    @Override
    public Topic create(Integer courseId, Integer sectionOrder, Topic topic) {
        if(courseId == null || !ramManager.getCourses().containsKey(courseId)){
            throw new IllegalArgumentException();
        }
        Course course = ramManager.getCourses().get(courseId);
        for(Section section : course.getSections()){
            if(sectionOrder.equals(section.getOrder())){
                topic.setSection(section);
            }
        }
        topic.setId(ramManager.getNewTopicId());
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

    @Override
    public List<Topic> getAll(Integer courseId) {
        List<Topic> topics = new ArrayList<>();
        if(courseId == null){
            return topics;
        }
        for (Topic topic : ramManager.getTopics().values()) {
            if (courseId.equals(topic.getSection().getCourse().getId())) {
                topics.add(topic);
            }
        }
        return topics;
    }
}
