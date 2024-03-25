package org.artem.courses.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Block {
    @Id
    @GeneratedValue
    private Integer pk;
    @ManyToOne
    private Topic topic;
    private Integer position;

    private UUID uuid;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Resource> resources = new ArrayList<>();

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer id) {
        this.pk = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer order) {
        this.position = order;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}
