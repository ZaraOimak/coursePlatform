package org.artem.courses.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthorDTO {
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    private String name;
    private List<Integer> coursesIds = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(List<Integer> coursesIds) {
        this.coursesIds = coursesIds;
    }
}
