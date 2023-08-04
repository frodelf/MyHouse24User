package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.Invoice;
import com.avada.MyHouse24User.repo.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl {
    public final InvoiceRepository invoiceRepository;
    public Invoice getById(long id){
        return invoiceRepository.findById(id).get();
    }
}
