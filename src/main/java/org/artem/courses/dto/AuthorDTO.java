package org.artem.courses.dto;

import org.artem.courses.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    private Integer id;
    private String name;
    private List<Integer> coursesIds = new ArrayList<>();

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

    public List<Integer> getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(List<Integer> coursesIds) {
        this.coursesIds = coursesIds;
    }
}
