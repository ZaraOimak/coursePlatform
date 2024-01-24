package org.artem.Entities;
public class Theme {
    private Integer idTheme;
    private Integer idSection;
    private String nameTheme;
    private String descriptionTheme;
    private Block block;

    public Integer getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public Integer getIdSection() {
        return idSection;
    }

    public void setIdSection(Integer idSection) {
        this.idSection = idSection;
    }

    public String getNameTheme() {
        return nameTheme;
    }

    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }

    public String getDescriptionTheme() {
        return descriptionTheme;
    }

    public void setDescriptionTheme(String descriptionTheme) {
        this.descriptionTheme = descriptionTheme;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public LinkToTheme getLinkToTheme() {
        return linkToTheme;
    }

    public void setLinkToTheme(LinkToTheme linkToTheme) {
        this.linkToTheme = linkToTheme;
    }

    private LinkToTheme linkToTheme;
}
