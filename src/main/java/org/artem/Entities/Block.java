package org.artem.Entities;
public class Block {
    private Integer idBlock;
    private Integer idTheme;
    private String nameBlock;
    private Resources resources;

    public Integer getIdBlock() {
        return idBlock;
    }

    public void setIdBlock(Integer idBlock) {
        this.idBlock = idBlock;
    }

    public Integer getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public String getNameBlock() {
        return nameBlock;
    }

    public void setNameBlock(String nameBlock) {
        this.nameBlock = nameBlock;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
