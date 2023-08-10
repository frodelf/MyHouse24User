package com.avada.MyHouse24User.services;


import com.avada.MyHouse24User.entity.User;

public interface UserService {
    User register(User user);
    User getByFirstName(String name);
    User findUserByEmail(String email);
    boolean verifyPassword(User user, String password);
    void save(User user);
}
