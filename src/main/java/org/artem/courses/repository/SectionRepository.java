package org.artem.courses.repository;

import org.artem.courses.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section,Integer> {
}
