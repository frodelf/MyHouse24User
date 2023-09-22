package com.avada.MyHouse24User.mapper;

import com.avada.MyHouse24User.entity.MasterRequest;
import com.avada.MyHouse24User.enums.MasterRequestStatus;
import com.avada.MyHouse24User.enums.RolesForMasterRequest;
import com.avada.MyHouse24User.model.MasterRequestDTO;
import com.avada.MyHouse24User.services.impl.RolesServiceImpl;
import com.avada.MyHouse24User.services.impl.UserServiceImpl;
import com.avada.MyHouse24User.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MasterRequestMapper {
    private final UserServiceImpl userService;
    public MasterRequestDTO toDto(MasterRequest masterRequest){
        MasterRequestDTO masterRequestDTO = new MasterRequestDTO();
        masterRequestDTO.setId(masterRequest.getId());
        if(masterRequest.getRole()!=null)masterRequestDTO.setRole(masterRequest.getRole());
        else masterRequestDTO.setRole(RolesForMasterRequest.AnySpecialist.getName());
        masterRequestDTO.setFlat(masterRequest.getFlat());
        masterRequestDTO.setDate(masterRequest.getDate());
        masterRequestDTO.setTime(masterRequest.getDate());
        masterRequestDTO.setDescription(masterRequest.getDescription());
        masterRequestDTO.setStatus(masterRequest.getStatus());
        return masterRequestDTO;
    }
    public MasterRequest toEntity(MasterRequestDTO masterRequestDTO){
        MasterRequest masterRequest = new MasterRequest();
        masterRequest.setId(masterRequest.getId());
        if(RolesForMasterRequest.getRoleByName(masterRequestDTO.getRole()) != null)masterRequest.setRole(masterRequestDTO.getRole());
        else masterRequest.setRole(null);
        masterRequest.setFlat(masterRequestDTO.getFlat());
        masterRequest.setDate(DateTimeUtil.combineDateTime(masterRequestDTO.getDate(), masterRequestDTO.getTime()));
        masterRequest.setDescription(masterRequestDTO.getDescription());
        masterRequest.setUser(userService.getAuthUser());
        masterRequest.setStatus(MasterRequestStatus.NEW.name());
        return masterRequest;
    }
    public List<MasterRequestDTO> toDtoList(List<MasterRequest> masterRequestList){
        List<MasterRequestDTO> masterRequestDTOList = new ArrayList<>();
        for (MasterRequest masterRequest : masterRequestList) {
            masterRequestDTOList.add(toDto(masterRequest));
        }
        return masterRequestDTOList;
    }
}
