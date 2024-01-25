package org.artem.entities;

import java.util.List;

public class Block {
    private Integer id;
    private Integer name;
    private Topic topic;
    private Integer order;
    private List <Resource> listResource;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<Resource> getListResource() {
        return listResource;
    }

    public void setListResource(List<Resource> listResource) {
        this.listResource = listResource;
    }
}
