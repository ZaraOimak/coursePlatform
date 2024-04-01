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
import java.util.UUID;

@Mapper(componentModel = "spring", uses = BlockMapper.class)
public abstract class TopicMapper {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private SectionRepository sectionRepository;


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
        BlockMapper blockMapper = Mappers.getMapper(BlockMapper.class);
        if (topicDTO.getBlocks() == null) {
            return entity.getBlocks();
        }
        List<Block> blocks = entity.getBlocks();
        if (topicDTO.getBlocks().isEmpty()) {
            blocks.clear();
        } else {
            List<UUID> uuids = topicDTO.getBlocks().stream().map(BlockDTO::getUuid).toList();
            blocks.removeIf(block -> !uuids.contains(block.getUuid()));

            for (BlockDTO blockDTO : topicDTO.getBlocks()) {
                Block block = getOrCreateBlock(entity, blockDTO.getUuid());
                blockMapper.updateBlockFromDTO(blockDTO, block);
                if (block.getId() == null) {
                    blocks.add(block);
                }
            }
        }
        return blocks;
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
