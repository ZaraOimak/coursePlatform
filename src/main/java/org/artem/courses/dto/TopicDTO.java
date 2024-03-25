package org.artem.courses.dto;

import java.util.List;
import java.util.UUID;

public class TopicDTO {
    private UUID uuid;
    private UUID sectionUuid;
    private String name;
    private String description;
    private UUID previousTopicUuid;
    private UUID nextTopicUuid;
    private List<BlockDTO> blocks;
    private Integer position;

    public UUID getPreviousTopicUuid() {
        return previousTopicUuid;
    }

    public void setPreviousTopicUuid(UUID previousTopicUuid) {
        this.previousTopicUuid = previousTopicUuid;
    }

    public UUID getNextTopicUuid() {
        return nextTopicUuid;
    }

    public void setNextTopicUuid(UUID nextTopicUuid) {
        this.nextTopicUuid = nextTopicUuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getSectionUuid() {
        return sectionUuid;
    }

    public void setSectionUuid(UUID sectionUuid) {
        this.sectionUuid = sectionUuid;
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


    public List<BlockDTO> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockDTO> blocks) {
        this.blocks = blocks;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
