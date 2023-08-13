package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.services.impl.FlatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tariff")
public class TariffController {
    private final FlatServiceImpl flatService;
    @GetMapping("/{id}")
    public String getById(@PathVariable("id")long id, Model model){
        model.addAttribute("flat", flatService.getById(id));
        return "view/tariff-get-by-id";
    }

}
