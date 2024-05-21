package org.artem.courses.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Block {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;
    private Integer position;
    private UUID uuid;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "parent")
    private List<Resource> resources = new ArrayList<>();

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
