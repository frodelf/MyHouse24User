package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.mapper.MasterRequestMapper;
import com.avada.MyHouse24User.model.MasterRequestDTO;
import com.avada.MyHouse24User.services.impl.MasterRequestServiceImpl;
import com.avada.MyHouse24User.util.DateTimeUtil;
import com.avada.MyHouse24User.validator.MasterRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/master-request")
public class MasterRequestController {
    private final MasterRequestServiceImpl masterRequestService;
    private final MasterRequestValidator masterRequestValidator;
    private final MasterRequestMapper masterRequestMapper;
    @GetMapping("/index")
    public String getAll(Model model){
        model.addAttribute("request", masterRequestService.getAll());
        return "view/master-get-all";
    }
    @GetMapping("/add")
    public String add(@ModelAttribute("request")MasterRequestDTO masterRequestDTO){
        return "view/master-add";
    }
    @PostMapping("/add")
    public String addPost(@ModelAttribute("request")MasterRequestDTO masterRequestDTO, BindingResult bindingResult){
        masterRequestValidator.validate(masterRequestDTO, bindingResult);
        if(bindingResult.hasErrors()){
            return "view/master-add";
        }
        masterRequestService.save(masterRequestMapper.toEntity(masterRequestDTO));
        return "redirect:/master-request/index";
    }
}
