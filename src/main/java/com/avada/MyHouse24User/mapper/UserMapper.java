package com.avada.MyHouse24User.mapper;

import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.enums.UserStatus;
import com.avada.MyHouse24User.model.UserDTO;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class UserMapper {
    public User toEntity(UserDTO userDTO){
        User user = new User();
        user.setId(Long.valueOf(userDTO.getId()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setFathersName(userDTO.getFathersName());
        user.setBirthday(Date.valueOf(userDTO.getBirthday()));
        user.setPhone(userDTO.getPhone());
        user.setViber(userDTO.getViber());
        user.setTelegram(userDTO.getTelegram());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setStatus(UserStatus.valueOf(userDTO.getStatus()));
        user.setDescription(userDTO.getDescription());
        user.setImage(userDTO.getImageName());
        return user;
    }
    public UserDTO toDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId().toString());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setFathersName(user.getFathersName());
        if(user.getBirthday() != null) userDTO.setBirthday(user.getBirthday().toLocalDate());
        userDTO.setPhone(user.getPhone());
        userDTO.setViber(user.getViber());
        userDTO.setTelegram(user.getTelegram());
        userDTO.setEmail(user.getEmail());
        userDTO.setStatus(user.getStatus().toString());
        userDTO.setDescription(user.getDescription());
        userDTO.setImageName(user.getImage());

        return userDTO;
    }
}
