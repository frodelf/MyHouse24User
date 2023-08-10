package com.avada.MyHouse24User.services;

import com.avada.MyHouse24User.entity.MasterRequest;

import java.util.List;

public interface MasterRequestService {
    List<MasterRequest> getAll();
    void save(MasterRequest masterRequest);
    void deleteById(long id);
}
