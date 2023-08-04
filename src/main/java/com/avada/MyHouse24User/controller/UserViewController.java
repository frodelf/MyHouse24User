package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.mapper.UserMapper;
import com.avada.MyHouse24User.model.UserDTO;
import com.avada.MyHouse24User.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserViewController {
    private final UserMapper userMapper;
    private final UserServiceImpl userService;
    @GetMapping("/view")
    public String userView(){
        return "view/user-view";
    }
    @GetMapping("/update")
    public String update(Model model){
        model.addAttribute("userDTO", userMapper.toDto(userService.getById(1)));
        return "view/user-update";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "view/user-update";
        }
        userService.save(userMapper.toEntity(userDTO));
        return "redirect:/user/view";
    }
}
