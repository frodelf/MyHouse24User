package com.avada.MyHouse24User.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    @GetMapping("/index")
    public String getAll(){
        return "view/message-get-all";
    }
}
