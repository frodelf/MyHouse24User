package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.entity.*;
import com.avada.MyHouse24User.mapper.UserMapper;
import com.avada.MyHouse24User.model.UserDTO;
import com.avada.MyHouse24User.repo.UserRepository;
import com.avada.MyHouse24User.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserViewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private UserMapper userMapper;

    @Test
    void userView() throws Exception {
        User user = new User();
        Flat flat = new Flat();

        House house = new House();
        flat.setHouse(house);
        flat.setFloor(new Floor("adsf"));
        flat.setSection(new Section("adsf"));
        Score score = new Score();
        score.setNumber("123456");
        flat.setScore(score);
        user.setFlats(Arrays.asList(flat));

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userService.getAuthUser()).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/user-view"));
    }

    @Test
    void update() throws Exception {
        User user = new User();
        Flat flat = new Flat();
        user.setImage("image");
        House house = new House();
        flat.setHouse(house);
        flat.setFloor(new Floor("adsf"));
        flat.setSection(new Section("adsf"));
        Score score = new Score();
        score.setNumber("123456");
        flat.setScore(score);
        user.setFlats(Arrays.asList(flat));
        UserDTO userDTO = new UserDTO();
        when(userMapper.toDto(any())).thenReturn(userDTO);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userService.getAuthUser()).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/user-update"))
                .andExpect(model().attributeExists("userDTO"));
    }

    @Test
    void testUpdate() throws Exception {
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userService.getAuthUser()).thenReturn(user);

        UserDTO userDTO = new UserDTO("1", "first", "last", "father", LocalDate.now(),
                "phone", "viber", "telegram", "email", "status", "description", (MultipartFile) new File("asdf"));


        mockMvc.perform(post("/user/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/user-update"));

        mockMvc.perform(post("/user/update")
                        .flashAttr("userDTO", userDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/view"));
    }
}