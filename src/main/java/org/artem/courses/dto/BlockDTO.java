package org.artem.courses.dto;

import org.artem.courses.entity.Resource;
import org.artem.courses.entity.Topic;

import java.util.ArrayList;
import java.util.List;

public class BlockDTO {
    private Integer id;
    private Integer order;
    private List<ResourceDTO> resources = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
