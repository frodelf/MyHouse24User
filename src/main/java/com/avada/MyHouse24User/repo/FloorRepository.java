package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FloorRepository extends JpaRepository<Floor, Long> {
    @Query(value = "SELECT MAX(id) FROM Floor")
    Long findMaxId();
}
