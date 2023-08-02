package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByFirstName(String name);
    Admin findByEmail(String email);
}
