package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
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
        userService.save(userToSave, new MockMultipartFile("qwerty", new byte[]{}));
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

    @Test
    void findUserByEmail(){
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        User result = userService.findUserByEmail(email);

        assertNull(result);
        verify(userRepository, times(1)).findByEmail(email);
    }
    @Test
    void verifyPassword(){
        User mockUser = new User();
        String password = "password";
        mockUser.setPassword(passwordEncoder.encode(password));

        when(passwordEncoder.matches(password, mockUser.getPassword())).thenReturn(true);

        boolean result = userService.verifyPassword(mockUser, password);

        assertTrue(result);
    }
    @Test
    void register(){
        User user = new User();
        String password = "password";
        user.setPassword(password);

        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        User savedUser = new User();
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.register(user);

        assertSame(savedUser, result);
//        assertEquals("encodedPassword", result.getPassword());
        verify(userRepository, times(1)).save(user);
    }
    @Test
    void getByFirstName(){
        String firstName = "John";
        User mockUser = new User();
        mockUser.setFirstName(firstName);

        when(userRepository.findByFirstName(firstName)).thenReturn(Optional.of(mockUser));

        User result = userService.getByFirstName(firstName);

        assertNotNull(result);
        assertEquals(firstName, result.getFirstName());
        verify(userRepository, times(1)).findByFirstName(firstName);
    }
}