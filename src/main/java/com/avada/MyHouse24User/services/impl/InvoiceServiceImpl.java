package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.Flat;
import com.avada.MyHouse24User.entity.Invoice;
import com.avada.MyHouse24User.model.InvoiceDTO;
import com.avada.MyHouse24User.repo.InvoiceRepository;
import com.avada.MyHouse24User.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    public final InvoiceRepository invoiceRepository;
    public final UserServiceImpl userService;
    public Invoice getById(long id){
        return invoiceRepository.findById(id).get();
    }
    public List<Invoice> filter(InvoiceDTO invoiceDTO){
        List<Invoice> invoices = new ArrayList<>();
        for (Flat flat : userService.getAuthUser().getFlats()) {
            for (Invoice invoice : flat.getInvoices()) {
                invoices.add(invoice);
            }
        }
        if(invoiceDTO.getDate() != null){
            invoices = invoices.stream()
                    .filter(dto -> dto.getDate() != null
                            &&  dto.getDate().getYear() == invoiceDTO.getDate().getYear()
                            &&  dto.getDate().getMonth() == invoiceDTO.getDate().getMonth()
                            &&  dto.getDate().getDay() == invoiceDTO.getDate().getDay())
                    .collect(Collectors.toList());
        }
        if(invoiceDTO.getStatus() != null){
            invoices = invoices.stream()
                    .filter(dto -> dto.getStatus() != null  &&  dto.getStatus().contains(invoiceDTO.getStatus()))
                    .collect(Collectors.toList());
        }
        return invoices;
    }
}
