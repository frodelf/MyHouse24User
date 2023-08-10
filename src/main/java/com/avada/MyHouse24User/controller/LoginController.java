package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.services.impl.UserServiceImpl;
import com.avada.MyHouse24User.services.registration.RegistrationRequest;
import com.avada.MyHouse24User.services.registration.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/cabinet")
public class LoginController {
    private UserServiceImpl userServiceImpl;
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String registerPage() {
        return "view/registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute RegistrationRequest registrationRequest) {
        if (userServiceImpl.findUserByEmail(registrationRequest.getEmail()) == null) {
            log.info("IN registerUser: user {} was added", registrationRequest.getEmail());
            registrationService.register(registrationRequest);
        }
        return "redirect:/cabinet/login";
    }

    @GetMapping("/login/confirm")
    public String confirmRegister(@RequestParam String token) {
        log.info("IN confirmRegister:{}", token);
        if (token == null || !registrationService.confirm(token)) {
            log.info("IN confirmRegister: token is wrong or null: {}", token);
        }
        return "redirect:/cabinet/login";
    }

    @GetMapping("/login")
    public String login() {
        return "/view/login";
    }
}
