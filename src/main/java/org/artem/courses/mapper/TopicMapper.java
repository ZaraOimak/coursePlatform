package org.artem.courses.mapper;

import org.artem.courses.dto.BlockDTO;
import org.artem.courses.dto.TopicDTO;
import org.artem.courses.entity.Block;
import org.artem.courses.entity.Section;
import org.artem.courses.entity.Topic;
import org.artem.courses.repository.SectionRepository;
import org.artem.courses.repository.TopicRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = BlockMapper.class)
public abstract class TopicMapper {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private BlockMapper blockMapper;


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "section", expression = "java(uuidToSection(dto.getSectionUuid(),entity))")
    @Mapping(source = "previousTopicUuid", target = "previous")
    @Mapping(source = "nextTopicUuid", target = "next")
    @Mapping(target = "blocks", expression = "java(updateBlocks(dto,entity))")
    public abstract void updateTopicFromDto(TopicDTO dto, @MappingTarget Topic entity);

    @Mapping(target = "sectionUuid", source = "section")
    @Mapping(target = "previousTopicUuid", source = "previous")
    @Mapping(target = "nextTopicUuid", source = "next")
    public abstract TopicDTO toDTO(Topic topic);

    protected List<Block> updateBlocks(TopicDTO topicDTO, @MappingTarget Topic entity) {
        if (topicDTO.getBlocks() == null) {
            return entity.getBlocks();
        }
        List<Block> blocks = entity.getBlocks();
        if (topicDTO.getBlocks().isEmpty()) {
            blocks.clear();
        } else {
            Set<UUID> updatedUuids = topicDTO.getBlocks().stream().map(BlockDTO::getUuid).collect(Collectors.toSet());


            for (BlockDTO blockDTO : topicDTO.getBlocks()) {
                Block block = getOrCreateBlock(entity, blockDTO.getUuid());
                blockMapper.updateBlockFromDTO(blockDTO, block);
                if (block.getId() == null) {
                    updatedUuids.add(block.getUuid());
                    blocks.add(block);
                }
            }
            deleteRemovedBlocks(blocks,blocks.stream().map(Block::getUuid).filter(Predicate.not(updatedUuids::contains)).toList());
        }
        return blocks;
    }

    private void deleteRemovedBlocks(List<Block> blocks, List<UUID> uuidsToRemove) {
        for(UUID uuid : uuidsToRemove){
            Block block = blocks.stream().filter(it -> uuid.equals(it.getUuid())).findFirst().orElseThrow();
            Integer position = block.getPosition();
            blocks.remove(block);
            blocks.stream().filter(it -> it.getPosition() > position).forEach(it -> it.setPosition(it.getPosition()-1));
        }
    }

    private static Block getOrCreateBlock(Topic topic, UUID blockUuid) {
        if (blockUuid == null) {
            Block block = new Block();
            block.setUuid(UUID.randomUUID());
            block.setTopic(topic);
            block.setPosition(topic.getBlocks().stream().mapToInt(Block::getPosition).max().orElse(-1) + 1);
            return block;
        }
        return topic.getBlocks().stream().filter(block -> block.getUuid().equals(blockUuid)).findFirst().orElseThrow();
    }

    protected Topic uuidToTopic(UUID topicUuid) {
        if (topicUuid == null) {
            return null;
        }
        return topicRepository.getByUuid(topicUuid);
    }

    protected UUID topicToUuid(Topic topic) {
        if (topic == null) {
            return null;
        }
        return topic.getUuid();
    }

    protected Section uuidToSection(UUID sectionUuid, Topic entity) {
        if (sectionUuid == null) {
            return entity.getSection();
        }
        Section section = sectionRepository.getByUuid(sectionUuid);
        section.getTopics().remove(entity);
        section.getTopics().add(entity);
        return section;
    }


    protected UUID sectionToUuid(Section section) {
        if (section == null) {
            return null;
        }
        return section.getUuid();
    }

    public abstract List<TopicDTO> toDtoList(List<Topic> topics);
}
