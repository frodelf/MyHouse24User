package com.avada.MyHouse24User.services;

import com.avada.MyHouse24User.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User getById(long id);
    void save(User user, MultipartFile image);
    User getAuthUser();
    User findUserByEmail(String email);
    boolean verifyPassword(User user, String password);
    User getByFirstName(String name);
    User register(User user);
}
