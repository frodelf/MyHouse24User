package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.Flat;
import com.avada.MyHouse24User.repo.FlatRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class FlatServiceImplTest {
    @InjectMocks
    private FlatServiceImpl flatService;
    @Mock
    private FlatRepository flatRepository;
    @Test
    void getById() {
        long flatId = 1L;
        Flat mockFlat = new Flat();
        mockFlat.setId(flatId);
        when(flatRepository.findById(flatId)).thenReturn(java.util.Optional.of(mockFlat));
        Flat result = flatService.getById(flatId);
        assertEquals(flatId, result.getId());
        verify(flatRepository, times(1)).findById(flatId);
    }
}