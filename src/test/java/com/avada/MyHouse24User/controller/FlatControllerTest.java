package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.entity.Flat;
import com.avada.MyHouse24User.entity.House;
import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.repo.FlatRepository;
import com.avada.MyHouse24User.repo.UserRepository;
import com.avada.MyHouse24User.services.impl.FlatServiceImpl;
import com.avada.MyHouse24User.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FlatControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FlatRepository flatRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private FlatServiceImpl flatService;
    @Test
    @WithMockUser(username = "admin@gmail.com")
    void flat() throws Exception {
        User user = new User();
        House house = new House();
        house.setName("name");
        Flat flat = new Flat();
        flat.setId(1L);
        flat.setHouse(house);
        user.setFlats(Arrays.asList(flat));
        when(flatService.getById(anyLong())).thenReturn(flat);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        mockMvc.perform(get("/flat/1")).andExpect(status().isOk());
    }
}