package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.entity.MasterRequest;
import com.avada.MyHouse24User.mapper.MasterRequestMapper;
import com.avada.MyHouse24User.repo.MasterRequestRepository;
import com.avada.MyHouse24User.repo.UserRepository;
import com.avada.MyHouse24User.services.impl.MasterRequestServiceImpl;
import com.avada.MyHouse24User.validator.MasterRequestValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Test
    void getAll() throws Exception {
        MasterRequest masterRequest = new MasterRequest();
        List<MasterRequest> masterRequests = Arrays.asList(masterRequest);
        when(masterRequestRepository.findAll()).thenReturn(masterRequests);
        mockMvc.perform(MockMvcRequestBuilders.get("/master-request/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/master-get-all"))
                .andExpect(model().attributeExists("request"));
    }

    @Test
    void add() {
    }

    @Test
    void addPost() {
    }
}