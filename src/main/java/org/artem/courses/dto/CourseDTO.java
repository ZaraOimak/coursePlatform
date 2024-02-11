package org.artem.courses.dto;

import org.artem.courses.entity.Author;
import org.artem.courses.entity.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseDTO {
    private String name;
    private String description;
    private UUID authorUuid;
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


    public UUID getAuthorUuid() {
        return authorUuid;
    }

    public void setAuthorUuid(UUID authorUuid) {
        this.authorUuid = authorUuid;
    }

    private List<SectionDTO> sections = new ArrayList<>();

    public List<SectionDTO> getSections() {
        return sections;
    }

    public void setSections(List<SectionDTO> sections) {
        this.sections = sections;
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

}
