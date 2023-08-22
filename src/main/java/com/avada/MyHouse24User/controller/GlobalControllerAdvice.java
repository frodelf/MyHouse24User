package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.entity.Theme;
import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Log4j2
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final UserServiceImpl userService;
    @ModelAttribute("user")
    public User user(){
        return userService.getAuthUser();
    }
    @ModelAttribute("theme")
    public String theme(){
        if(userService.getAuthUser().getTheme()==null)return Theme.LIGHT.name();
        return userService.getAuthUser().getTheme().name();
    }
}