package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.CounterData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CounterDataRepository extends JpaRepository<CounterData, Long> {
    boolean existsByNumber(String number);
    @Query(value = "SELECT MAX(id) FROM CounterData")
    Long findMaxId();
}
