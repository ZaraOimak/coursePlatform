package org.artem.courses.service.impl;

import org.artem.courses.dto.TopicDTO;
import org.artem.courses.entity.Topic;
import org.artem.courses.mapper.TopicMapper;
import org.artem.courses.repository.TopicRepository;
import org.artem.courses.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

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
            remove(topic);
            topicRepository.delete(topic);
        }
    }

    private void remove(Topic topic) {
        Topic prev = topic.getPrevious();
        Topic next = topic.getNext();
        if(prev != null){
            prev.setNext(next);
            if(next != null){
                next.setPrevious(prev);
            }
        } else if(next != null) {
            next.setPrevious(null);
        }
        shiftNextTopics(next);
    }

    private void shiftNextTopics(Topic next) {
        if(next == null){
            return;
        }
        do {
            next.setPosition(next.getPosition()-1);
            next = next.getNext();
        } while(next != null);
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
        if (topic.getPosition() == null) {
            if (topic.getSection() == null) {
                throw new IllegalStateException("topic must have section");
            }
            int maxPosition = topic.getSection().getTopics().stream()
                    .filter(Predicate.not(topic::equals))
                    .mapToInt(Topic::getPosition)
                    .filter(Objects::nonNull).max().orElse(-1);
            topic.setPosition(maxPosition + 1);

            topic.getSection().getTopics().stream()
                    .filter(Predicate.not(topic::equals))
                    .filter(existingTopic -> existingTopic.getPosition().equals(maxPosition)).findFirst()
                    .ifPresent(previousTopic -> {
                        topic.setPrevious(previousTopic);
                        previousTopic.setNext(topic);
                    });
        }
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
