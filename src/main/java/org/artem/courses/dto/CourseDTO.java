package org.artem.courses.dto;

import org.artem.courses.entity.Author;
import org.artem.courses.entity.Section;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer authorId;
    private List<SectionDTO> sections = new ArrayList<>();

    public List<SectionDTO> getSections() {
        return sections;
    }

    public void setSections(List<SectionDTO> sections) {
        this.sections = sections;
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
