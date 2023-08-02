package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    boolean existsByNumber(String number);
    Optional<Score> findByNumber(String number);
    List<Score> findAllByStatus(String status);
    Page<Score> findByNumberContainingIgnoreCase(String search, Pageable pageable);
}