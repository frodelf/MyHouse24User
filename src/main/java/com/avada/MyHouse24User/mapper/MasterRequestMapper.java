package com.avada.MyHouse24User.mapper;

import com.avada.MyHouse24User.entity.MasterRequest;
import com.avada.MyHouse24User.enums.Roles;
import com.avada.MyHouse24User.model.MasterRequestDTO;
import com.avada.MyHouse24User.services.impl.RolesServiceImpl;
import com.avada.MyHouse24User.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MasterRequestMapper {
    private final RolesServiceImpl rolesService;
    public MasterRequestDTO toDto(MasterRequest masterRequest){
        MasterRequestDTO masterRequestDTO = new MasterRequestDTO();
        masterRequestDTO.setId(masterRequest.getId());
        masterRequestDTO.setRole(Roles.getNameByRole(masterRequest.getAdmin().getRole().getName()));
        masterRequestDTO.setFlat(masterRequest.getFlat());
        masterRequestDTO.setDate(masterRequest.getDate());
        masterRequestDTO.setTime(masterRequest.getDate());
        masterRequestDTO.setDescription(masterRequest.getDescription());
        return masterRequestDTO;
    }
    public MasterRequest toEntity(MasterRequestDTO masterRequestDTO){
        MasterRequest masterRequest = new MasterRequest();
        masterRequest.setId(masterRequest.getId());
        masterRequest.setRole(rolesService.getByName(Roles.getRoleByName(masterRequestDTO.getRole())));
        masterRequest.setFlat(masterRequestDTO.getFlat());
        masterRequest.setDate(DateTimeUtil.combineDateTime(masterRequestDTO.getDate(), masterRequestDTO.getTime()));
        masterRequest.setDescription(masterRequestDTO.getDescription());
        return masterRequest;
    }
}
