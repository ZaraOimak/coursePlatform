package org.artem.courses.mapper;

import org.artem.courses.dto.SectionDTO;
import org.artem.courses.entity.Section;
import org.artem.courses.entity.Topic;
import org.artem.courses.repository.TopicRepository;
import org.artem.courses.service.TopicService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SectionMapper {
    @Autowired
    private TopicRepository topicRepository;

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "topics", source = "topicsIds")
    public abstract void updateSectionFromDto(SectionDTO dto, @MappingTarget Section entity);

    @Mapping(target = "topicsIds", source = "topics")
    public abstract SectionDTO toDto(Section entity);

    public abstract List<SectionDTO> toDtoList(List<Section> sections);

    public abstract List<Section> fromDtoList(List<SectionDTO> sections);

    protected List<Topic> mapTopicsIds(List<UUID> topicsIds) {
        if (topicsIds == null) {
            return null;
        }
        return topicsIds.stream()
                .map(topicId -> topicRepository.getByUuid(topicId))
                .collect(Collectors.toList());
    }

    protected List<UUID> mapTopics(List<Topic> topics) {
        if (topics == null) {
            return null;
        }
        return topics.stream()
                .sorted(Comparator.comparingInt(Topic::getPosition))
                .map(Topic::getUuid)
                .collect(Collectors.toList());
    }


}
