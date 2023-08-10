package com.avada.MyHouse24User.services;

import com.avada.MyHouse24User.entity.User;

public interface UserService {
    User getById(long id);
    void save(User user);
    User getAuthUser();
}
