package org.artem.courses.service.impl;

import org.artem.courses.dto.TopicDTO;
import org.artem.courses.entity.Topic;
import org.artem.courses.mapper.TopicMapper;
import org.artem.courses.repository.TopicRepository;
import org.artem.courses.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultTopicService implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public DefaultTopicService(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.getSection().getTopics().remove(topic);
        topicRepository.delete(topic);
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        Topic topic = topicRepository.getByUuid(uuid);
        if (topic != null) {
            topic.getSection().getTopics().remove(topic);
            topicRepository.delete(topic);
        }
        topicRepository.deleteByUuid(uuid);
    }

    @Override
    @Transactional
    public Topic update(Topic topic) {
        if (topic.getUuid() == null) {
            topic.setUuid(UUID.randomUUID());
        } else {
            Topic saved = topicRepository.getByUuid(topic.getUuid());
            if (saved != null) {
                topic.setId(saved.getId());
            }
        }

        return topicRepository.save(topic);
    }

    @Override
    public TopicDTO update(TopicDTO topicDto) {
        Topic topic = getTopic(topicDto.getUuid());
        topicMapper.updateTopicFromDto(topicDto, topic);
        topicRepository.save(topic);
        return topicMapper.toDTO(topic);
    }

    private Topic getTopic(UUID uuid) {
        Topic topic = topicRepository.getByUuid(uuid);
        if (topic == null) {
            topic = new Topic();
            topic.setUuid(UUID.randomUUID());
        }
        return topic;
    }


    @Override
    @Transactional(readOnly = true)
    public Topic getById(Integer id) {
        return topicRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public Topic getByUuid(UUID uuid) {
        return topicRepository.getByUuid(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Topic> getAll(Integer courseId) {
        return topicRepository.findAllBySection_Course_Id(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Topic> getAll(UUID courseUuid) {
        return topicRepository.findAllBySection_Course_Uuid(courseUuid);
    }

    @Override
    @Transactional(readOnly = true)
    public TopicDTO getByUuidDto(UUID uuid) {
        return topicMapper.toDTO(getByUuid(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TopicDTO> getAllDto(UUID courseUuid) {
        return topicMapper.toDtoList(getAll(courseUuid));
    }

}
