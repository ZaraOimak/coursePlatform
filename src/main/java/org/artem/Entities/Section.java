package org.artem.Entities;
public class Section {
    private Integer idSection;
    private Integer idCourse;
    private String nameSection;
    private String descriptionSection;
    private Theme theme;

    public Integer getIdSection() {
        return idSection;
    }

    public void setIdSection(Integer idSection) {
        this.idSection = idSection;
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameSection() {
        return nameSection;
    }

    public void setNameSection(String nameSection) {
        this.nameSection = nameSection;
    }

    public String getDescriptionSection() {
        return descriptionSection;
    }

    public void setDescriptionSection(String descriptionSection) {
        this.descriptionSection = descriptionSection;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}