package org.artem.courses.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BlockDTO {
    private UUID uuid;
    private Integer order;
    private List<ResourceDTO> resources = new ArrayList<>();


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<ResourceDTO> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDTO> resources) {
        this.resources = resources;
    }
}
