package org.artem.courses.repository;

import org.artem.courses.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    void deleteByUuid(UUID uuid);

    Topic getByUuid(UUID uuid);

    List<Topic> findAllBySection_Course_Uuid(UUID courseUuid);

    List<Topic> findAllBySection_Course_Id(Integer courseId);
}
