package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.Flat;
import com.avada.MyHouse24User.entity.Invoice;
import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.model.InvoiceDTO;
import com.avada.MyHouse24User.repo.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class InvoiceServiceImplTest {
    @InjectMocks
    private InvoiceServiceImpl invoiceService;
    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private UserServiceImpl userService;
    @Test
    void getById() {
        long invoiceId = 1L;
        Invoice mockInvoice = new Invoice();
        mockInvoice.setId(invoiceId);

        when(invoiceRepository.findById(invoiceId)).thenReturn(java.util.Optional.of(mockInvoice));

        Invoice result = invoiceService.getById(invoiceId);

        assertEquals(invoiceId, result.getId());

        verify(invoiceRepository, times(1)).findById(invoiceId);
    }

    @Test
    void filter() {
        User mockUser = new User();
        mockUser.setFlats(new ArrayList<>());
        Flat flat1 = new Flat();
        flat1.setId(1L);
        flat1.setInvoices(new ArrayList<>());
        Flat flat2 = new Flat();
        flat2.setId(2L);
        flat2.setInvoices(new ArrayList<>());
        mockUser.getFlats().add(flat1);
        mockUser.getFlats().add(flat2);

        Invoice invoice1 = new Invoice();
        invoice1.setStatus("Paid");
        invoice1.setDate(Date.valueOf(LocalDate.of(2023, 8, 10)));
        flat1.getInvoices().add(invoice1);

        Invoice invoice2 = new Invoice();
        invoice2.setStatus("Pending");
        invoice2.setDate(Date.valueOf(LocalDate.of(2023, 8, 10)));
        flat2.getInvoices().add(invoice2);

        when(userService.getAuthUser()).thenReturn(mockUser);

        InvoiceDTO filterDTO = new InvoiceDTO();
        filterDTO.setStatus("Paid");
        filterDTO.setDate(Date.valueOf(LocalDate.of(2023, 8, 10)));

        List<Invoice> expectedInvoices = new ArrayList<>();
        expectedInvoices.add(invoice1);

        List<Invoice> result = invoiceService.filter(filterDTO);

        assertEquals(expectedInvoices, result);

        verify(userService, times(1)).getAuthUser();
    }
}