package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.MasterRequest;
import com.avada.MyHouse24User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterRequestRepository extends JpaRepository<MasterRequest, Long> {
    List<MasterRequest> findAllByUser(User user);
}
