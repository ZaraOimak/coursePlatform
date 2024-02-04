package org.artem.courses.dto;

import org.artem.courses.entity.Block;
import org.artem.courses.entity.ResourceType;

public class ResourceDTO {
    private Integer id;
    private Integer order;
    private ResourceType resourceType;
    private String content;

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

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
