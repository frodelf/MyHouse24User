package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findByName(String name);
    @Query(value = "SELECT MAX(id) FROM Section")
    Long findMaxId();
}
