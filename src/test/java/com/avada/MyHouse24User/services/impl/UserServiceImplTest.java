package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Test
    void getById() {
        User mockUser = new User();
        mockUser.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        User result = userService.getById(1L);
        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void save() {
        User userToSave = new User();
        userToSave.setId(1L);
        userService.save(userToSave);
        verify(userRepository, times(1)).save(userToSave);
    }

    @Test
    void getAuthUser() {
        User mockUser = new User();
        mockUser.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        User result = userService.getAuthUser();
        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(1L);
    }
}