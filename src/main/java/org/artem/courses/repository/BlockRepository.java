package org.artem.courses.repository;

import org.artem.courses.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Integer> {
}
