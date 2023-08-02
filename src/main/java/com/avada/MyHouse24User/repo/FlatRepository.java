package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FlatRepository extends JpaRepository<Flat, Long> {
    @Query(value = "SELECT MAX(id) FROM Flat")
    Long findMaxId();
    Optional<Flat> findByNumber(int number);
}
