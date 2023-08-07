package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.Flat;
import com.avada.MyHouse24User.entity.MasterRequest;
import com.avada.MyHouse24User.repo.MasterRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterRequestServiceImpl {
    private final MasterRequestRepository masterRequestRepository;
    private final UserServiceImpl userService;
    public List<MasterRequest> getAll(){
        return masterRequestRepository.findAllByUser(userService.getAuthUser());
    }
    public void save(MasterRequest masterRequest){
        masterRequestRepository.save(masterRequest);
    }
    public void deleteById(long id){
        masterRequestRepository.deleteById(id);
    }
}
