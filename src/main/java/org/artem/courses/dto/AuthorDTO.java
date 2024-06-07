package org.artem.courses.dto;

import java.util.List;
import java.util.UUID;

public class AuthorDTO {
    private UUID uuid;
    private String name;
    private String avatar;
    private List<UUID> coursesUuids;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<UUID> getCoursesUuids() {
        return coursesUuids;
    }

    public void setCoursesUuids(List<UUID> coursesUuids) {
        this.coursesUuids = coursesUuids;
    }
}
