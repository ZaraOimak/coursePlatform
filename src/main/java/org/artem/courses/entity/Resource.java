package org.artem.courses.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Resource {
    @ManyToOne
    @JoinColumn(name = "block_id",referencedColumnName = "id")
    private Block parent;
    @Id
    @GeneratedValue
    private Integer id;
    private UUID uuid;
    private Integer position;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ResourceType resourceType;
    private String content;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Block getParent() {
        return parent;
    }

    public void setParent(Block parent) {
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
