package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.Chat;
import com.avada.MyHouse24User.entity.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Page<Chat> findAllByHouse(House house, Pageable pageable);
}
