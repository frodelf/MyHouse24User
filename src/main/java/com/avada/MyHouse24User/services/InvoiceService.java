package com.avada.MyHouse24User.services;

import com.avada.MyHouse24User.entity.Invoice;
import com.avada.MyHouse24User.model.InvoiceDTO;

import java.util.List;

public interface InvoiceService {
    Invoice getById(long id);
    List<Invoice> filter(InvoiceDTO invoiceDTO);
}
