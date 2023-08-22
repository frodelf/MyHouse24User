package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.entity.Theme;
import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.mapper.UserMapper;
import com.avada.MyHouse24User.model.UserDTO;
import com.avada.MyHouse24User.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserViewController {
    private final UserMapper userMapper;
    private final UserServiceImpl userService;
    @GetMapping("/view")
    public String userView(Model model){
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
        userService.save(userMapper.toEntity(userDTO), userDTO.getImage());
        return "redirect:/user/view";
    }
    @GetMapping("/change/theme/{theme}")
    @ResponseBody
    public void changeTheme(@PathVariable("theme")String theme){
        User user = userService.getAuthUser();
        if(theme.equals("light")){
            user.setTheme(Theme.LIGHT);
        } else if (theme.equals("dark")) {
            user.setTheme(Theme.DARK);
        }
        userService.save(user);
    }
}
