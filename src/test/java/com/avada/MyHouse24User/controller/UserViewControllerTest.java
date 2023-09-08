package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.entity.*;
import com.avada.MyHouse24User.mapper.UserMapper;
import com.avada.MyHouse24User.model.UserDTO;
import com.avada.MyHouse24User.repo.UserRepository;
import com.avada.MyHouse24User.services.UserService;
import com.avada.MyHouse24User.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
    @WithMockUser(username = "admin@gmail.com")
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
    @WithMockUser(username = "admin@gmail.com")
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
    @WithMockUser(username = "admin@gmail.com")
    void testUpdate() throws Exception {
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userService.getAuthUser()).thenReturn(user);

        UserDTO userDTO = new UserDTO("1", "first", "last", "father", LocalDate.now(),
                "phone", "viber", "telegram", "email", "status", "description", new MockMultipartFile("1", new byte[1]));


        mockMvc.perform(post("/user/update")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("view/user-update"));

        mockMvc.perform(post("/user/update")
                        .with(csrf())
                        .flashAttr("userDTO", userDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/view"));
    }
    @Test
    void testChangeTheme() throws Exception {
        User user = new User();
        user.setFirstName("testuser");
        Mockito.when(userService.getAuthUser()).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/change/theme/light"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/user/change/theme/dark"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(userService, Mockito.times(2)).save(user);
    }
}