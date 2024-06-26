package org.artem.courses.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Section {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
    private String name;
    private String description;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "section")
    private List<Topic> topics = new ArrayList<>();
    private Integer position;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}