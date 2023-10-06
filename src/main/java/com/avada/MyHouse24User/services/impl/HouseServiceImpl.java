package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.House;
import com.avada.MyHouse24User.repo.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl {
    private final HouseRepository houseRepository;
    public House getById(Long id){
        return houseRepository.findById(id).get();
    }
}
