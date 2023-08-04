package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.enums.InvoiceStatus;
import com.avada.MyHouse24User.services.impl.FlatServiceImpl;
import com.avada.MyHouse24User.services.impl.InvoiceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    public final FlatServiceImpl flatService;
    public final InvoiceServiceImpl invoiceService;

    @GetMapping("/index")
    public String getAll(Model model) {
        model.addAttribute("statuses", InvoiceStatus.getAll());
        return "view/invoice-get-all";
    }

    @GetMapping("/index/{id}")
    public String getById(@PathVariable("id") long id, Model model) {
        model.addAttribute("statuses", InvoiceStatus.getAll());
        model.addAttribute("flat", flatService.getById(id));
        return "view/invoice-get-by-id";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable("id") long id, Model model) {
        model.addAttribute("invoice", invoiceService.getById(id));
        return "view/invoice-details";
    }
}