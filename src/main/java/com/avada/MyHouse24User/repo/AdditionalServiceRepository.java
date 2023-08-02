package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.AdditionalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long> {
    Optional<AdditionalService> findByName(String name);
    Page<AdditionalService> findByNameContainingIgnoreCase(String search, Pageable pageable);
}
