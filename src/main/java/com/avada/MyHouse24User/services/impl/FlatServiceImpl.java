package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.Flat;
import com.avada.MyHouse24User.repo.FlatRepository;
import com.avada.MyHouse24User.services.FlatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlatServiceImpl implements FlatService {
    private final FlatRepository flatRepository;
    @Override
    public Flat getById(long id) {
        return flatRepository.findById(id).get();
    }
}
