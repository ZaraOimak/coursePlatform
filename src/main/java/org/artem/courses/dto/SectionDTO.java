package org.artem.courses.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.artem.courses.entity.Course;
import org.artem.courses.entity.Topic;

import java.util.ArrayList;
import java.util.List;

public class SectionDTO {
    private Integer id;
    private String name;
    private String description;
    private List<Integer> topicsIds = new ArrayList<>();
    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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