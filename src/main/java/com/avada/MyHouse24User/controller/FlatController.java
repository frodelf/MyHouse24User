package com.avada.MyHouse24User.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flat")
public class FlatController {
    @GetMapping("/{id}")
    public String flat(@PathVariable("id")long id){
        return "view/flat";
    }
}
