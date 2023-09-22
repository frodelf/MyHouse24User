package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.Role;
import com.avada.MyHouse24User.repo.RoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RolesForMasterRequestServiceImplTest {
    @InjectMocks
    private RolesServiceImpl rolesService;
    @Mock
    private RoleRepository roleRepository;
    @Test
    void getByName() {
        Role mockRole = new Role();
        mockRole.setId(1L);
        mockRole.setName("ROLE_USER");

        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(mockRole));

        Role result = rolesService.getByName("ROLE_USER");

        assertEquals(1L, result.getId());
        assertEquals("ROLE_USER", result.getName());

        verify(roleRepository, times(1)).findByName("ROLE_USER");
    }
}