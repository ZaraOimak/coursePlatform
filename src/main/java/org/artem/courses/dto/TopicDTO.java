package org.artem.courses.dto;

import org.artem.courses.entity.Block;
import org.artem.courses.entity.Section;
import org.artem.courses.entity.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TopicDTO {
    private UUID uuid;
    private Integer sectionId;
    private String name;
    private String description;
    private Integer previousTopicId;
    private Integer nextTopicId;
    private List<BlockDTO> blocks = new ArrayList<>();
    private Integer order;
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPreviousTopicId() {
        return previousTopicId;
    }

    public void setPreviousTopicId(Integer previousTopicId) {
        this.previousTopicId = previousTopicId;
    }

    public Integer getNextTopicId() {
        return nextTopicId;
    }

    public void setNextTopicId(Integer nextTopicId) {
        this.nextTopicId = nextTopicId;
    }

    public List<BlockDTO> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockDTO> blocks) {
        this.blocks = blocks;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
