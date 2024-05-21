package org.artem.courses.repository;

import org.artem.courses.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    Section getByUuidAndCourseId(UUID uuid, Integer courseId);

    Section getByUuid(UUID uuid);

}
