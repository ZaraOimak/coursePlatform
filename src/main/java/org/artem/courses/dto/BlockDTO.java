package org.artem.courses.dto;

import java.util.List;
import java.util.UUID;

public class BlockDTO {
    private UUID uuid;
    private Integer position;
    private List<ResourceDTO> resources;


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<ResourceDTO> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDTO> resources) {
        this.resources = resources;
    }
}
