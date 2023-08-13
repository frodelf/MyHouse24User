package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.entity.Flat;
import com.avada.MyHouse24User.entity.House;
import com.avada.MyHouse24User.entity.Invoice;
import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.enums.InvoiceStatus;
import com.avada.MyHouse24User.model.InvoiceDTO;
import com.avada.MyHouse24User.repo.FlatRepository;
import com.avada.MyHouse24User.repo.UserRepository;
import com.avada.MyHouse24User.services.impl.FlatServiceImpl;
import com.avada.MyHouse24User.services.impl.InvoiceServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    public FlatServiceImpl flatService;
    @MockBean
    public InvoiceServiceImpl invoiceService;
    @MockBean
    private FlatRepository flatRepository;
    @MockBean
    private UserRepository userRepository;
    @Mock
    private Model model;
    @Test
    void getAll() throws Exception {
        User user = new User();
        House house = new House();
        house.setName("name");
        Flat flat = new Flat();
        flat.setId(1L);
        flat.setHouse(house);
        user.setFlats(Arrays.asList(flat));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(flatService.getById(anyLong())).thenReturn(flat);

        mockMvc.perform(MockMvcRequestBuilders.get("/invoice/index/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/invoice-get-by-id"))
                .andExpect(model().attributeExists("flat", "statuses"));
    }

    @Test
    void getById() throws Exception {
        User user = new User();
        House house = new House();
        house.setName("name");
        Flat flat = new Flat();
        flat.setId(1L);
        flat.setHouse(house);
        user.setFlats(Arrays.asList(flat));
        when(flatService.getById(anyLong())).thenReturn(flat);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/invoice/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/invoice-get-all"))
                .andExpect(model().attributeExists("invoiceDTO", "statuses"));
    }

    @Test
    void details() throws Exception {
        User user = new User();
        Flat flat = new Flat();
        flat.setId(1L);
        House house = new House();
        house.setName("name");
        flat.setHouse(house);
        user.setFlats(Arrays.asList(flat));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Invoice mockInvoice = new Invoice();
        mockInvoice.setId(1L);
        when(invoiceService.getById(1L)).thenReturn(mockInvoice);

        mockMvc.perform(MockMvcRequestBuilders.get("/invoice/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/invoice-details"))
                .andExpect(model().attributeExists("invoice"));
    }

    @Test
    void filter() throws Exception {
        User user = new User();
        House house = new House();
        house.setName("name");
        Flat flat = new Flat();
        flat.setId(1L);
        flat.setHouse(house);
        user.setFlats(Arrays.asList(flat));
        when(flatService.getById(anyLong())).thenReturn(flat);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/invoice/filter"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/invoice-filter"))
                .andExpect(model().attributeExists("invoices", "statuses"));
    }
}