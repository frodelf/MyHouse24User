package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.Role;
import com.avada.MyHouse24User.repo.RoleRepository;
import com.avada.MyHouse24User.services.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {
    private final RoleRepository roleRepository;
    public Role getByName(String name){
        return roleRepository.findByName(name).get();
    }
}
