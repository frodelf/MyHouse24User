package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.entity.*;
import com.avada.MyHouse24User.mapper.MasterRequestMapper;
import com.avada.MyHouse24User.model.MasterRequestDTO;
import com.avada.MyHouse24User.repo.MasterRequestRepository;
import com.avada.MyHouse24User.repo.UserRepository;
import com.avada.MyHouse24User.services.impl.MasterRequestServiceImpl;
import com.avada.MyHouse24User.validator.MasterRequestValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MasterRequestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MasterRequestServiceImpl masterRequestService;
    @MockBean
    private MasterRequestRepository masterRequestRepository;
    @MockBean
    private MasterRequestValidator masterRequestValidator;
    @MockBean
    private MasterRequestMapper masterRequestMapper;
    @Mock
    private BindingResult bindingResult;
    @Test
    void getAll() throws Exception {
        User user = new User();
        Flat flat = new Flat();

        House house = new House();
        flat.setHouse(house);
        user.setFlats(Arrays.asList(flat));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        MasterRequest masterRequest = new MasterRequest();
        List<MasterRequest> masterRequests = Arrays.asList(masterRequest);
        when(masterRequestRepository.findAll()).thenReturn(masterRequests);
        mockMvc.perform(MockMvcRequestBuilders.get("/master-request/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/master-get-all"))
                .andExpect(model().attributeExists("request"));
    }

    @Test
    void add() throws Exception {
        User user = new User();
        Flat flat = new Flat();
        House house = new House();
        flat.setHouse(house);
        user.setFlats(Arrays.asList(flat));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/master-request/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/master-add"));
    }

    @Test
    void addPost() throws Exception {
        User user = new User();
        Flat flat = new Flat();
        House house = new House();
        flat.setHouse(house);
        user.setFlats(Arrays.asList(flat));
        MasterRequestDTO masterRequestDTO = new MasterRequestDTO();
        masterRequestDTO.setDescription("qqq");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "request");
        bindingResult.reject("someErrorCode");

        mockMvc.perform(MockMvcRequestBuilders.post("/master-request/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/master-add"));
        mockMvc.perform(MockMvcRequestBuilders.post("/master-request/add")
                        .flashAttr("request", masterRequestDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/master-request/index"));
    }
}