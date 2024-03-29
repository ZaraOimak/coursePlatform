package org.artem.courses.entity;

import java.util.List;

public class Topic {
    private Integer id;
    private Section section;
    private String name;
    private String description;
    private Topic previous;
    private Topic next;

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

    private List<Block> blocks;
    private Integer order;

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
