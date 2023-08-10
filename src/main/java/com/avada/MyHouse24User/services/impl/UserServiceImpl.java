package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.repo.UserRepository;
import com.avada.MyHouse24User.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserByEmail(String email) {
        try {
            return userRepository.findByEmail(email).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public boolean verifyPassword(User user, String password) {
        log.info("IN verifyPassword password: {}", passwordEncoder.matches(password, user.getPassword()));
        return passwordEncoder.matches(password, user.getPassword());
    }

    public User getById(long id){
        return userRepository.findById(id).get();
    }
    public void save(User user){
        userRepository.save(user);
    }
    public User getAuthUser(){
        return userRepository.findById(1L).get();
    }
    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public User getByFirstName(String name) {
        return userRepository.findByFirstName(name).get();    }
}
