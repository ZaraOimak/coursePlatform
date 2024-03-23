package org.artem.courses.dto;

import java.util.List;
import java.util.UUID;

public class SectionDTO {
    private UUID uuid;
    private String name;
    private String description;
    private List<UUID> topicsIds;
    private Integer position;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public List<UUID> getTopicsIds() {
        return topicsIds;
    }

    public void setTopicsIds(List<UUID> topicsIds) {
        this.topicsIds = topicsIds;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
