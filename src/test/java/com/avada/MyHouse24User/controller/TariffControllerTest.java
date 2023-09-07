package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.entity.Flat;
import com.avada.MyHouse24User.entity.House;
import com.avada.MyHouse24User.entity.Tariff;
import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.repo.FlatRepository;
import com.avada.MyHouse24User.repo.UserRepository;
import com.avada.MyHouse24User.services.impl.FlatServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TariffControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FlatServiceImpl flatService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private FlatRepository flatRepository;
    @Test
    @WithMockUser(username = "admin@gmail.com")
    void getById() throws Exception {
        User user = new User();
        Flat flat = new Flat();
        House house = new House();
        Tariff tariff = new Tariff();
        tariff.setTariffAdditionalService(new ArrayList<>());
        house.setName("qwerty");
        flat.setHouse(house);
        flat.setTariff(tariff);
        user.setFlats(Arrays.asList(flat));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(flatRepository.findById(anyLong())).thenReturn(Optional.of(flat));
        when(flatService.getById(anyLong())).thenReturn(flat);
        mockMvc.perform(MockMvcRequestBuilders.get("/tariff/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/tariff-get-by-id"))
                .andExpect(model().attributeExists("flat"));
    }
}