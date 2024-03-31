package org.artem.courses.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Topic {
    @Id
    @GeneratedValue
    private Integer id;
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;
    private String name;
    private String description;
    @OneToOne
    @JoinColumn(name = "prev_topic_id", referencedColumnName = "id")
    private Topic previous;
    @OneToOne
    @JoinColumn(name = "next_topic_id", referencedColumnName = "id")
    private Topic next;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "topic")
    private List<Block> blocks = new ArrayList<>();
    private Integer position;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Topic getPrevious() {
        return previous;
    }

    public void setPrevious(Topic previous) {
        this.previous = previous;
    }

    public Topic getNext() {
        return next;
    }

    public void setNext(Topic next) {
        this.next = next;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
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

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer order) {
        this.position = order;
    }
}
