package org.artem.Entities;
public class Course {
    private Integer idCourse;
    private String nameCourse;
    private String descriptionCourse;
    private String nameAuthor;
    private Section section;
    private Author author;

    public Integer getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getDescriptionCourse() {
        return descriptionCourse;
    }

    public void setDescriptionCourse(String descriptionCourse) {
        this.descriptionCourse = descriptionCourse;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
