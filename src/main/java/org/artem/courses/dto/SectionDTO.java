package org.artem.courses.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SectionDTO {
    private UUID uuid;
    private String name;
    private String description;
    private List<Integer> topicsIds = new ArrayList<>();
    private Integer order;

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

    public List<Integer> getTopicsIds() {
        return topicsIds;
    }

    public void setTopicsIds(List<Integer> topicsIds) {
        this.topicsIds = topicsIds;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
