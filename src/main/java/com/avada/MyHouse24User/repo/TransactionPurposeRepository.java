package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.TransactionPurpose;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionPurposeRepository extends JpaRepository<TransactionPurpose, Long> {
    Optional<TransactionPurpose> findByName(String name);
}
