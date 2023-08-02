package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.UnitOfMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitOfMeasurementRepository extends JpaRepository<UnitOfMeasurement, Long> {
    Optional<UnitOfMeasurement> findByName(String name);
}
