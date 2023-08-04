package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;
    public User getById(long id){
        return userRepository.findById(id).get();
    }
    public void save(User user){
        userRepository.save(user);
    }
}
