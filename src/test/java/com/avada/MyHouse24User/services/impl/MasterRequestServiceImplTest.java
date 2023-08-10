package com.avada.MyHouse24User.services.impl;

import com.avada.MyHouse24User.entity.MasterRequest;
import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.repo.MasterRequestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class MasterRequestServiceImplTest {
    @InjectMocks
    private MasterRequestServiceImpl masterRequestService;
    @Mock
    private MasterRequestRepository masterRequestRepository;
    @Mock
    private UserServiceImpl userService;
    @Test
    void getAll() {
        MasterRequest mockRequest1 = new MasterRequest();
        MasterRequest mockRequest2 = new MasterRequest();
        List<MasterRequest> mockRequests = new ArrayList<>();
        mockRequests.add(mockRequest1);
        mockRequests.add(mockRequest2);

        User user = new User();
        user.setId(1L);

        when(userService.getAuthUser()).thenReturn(user);
        when(masterRequestRepository.findAllByUser(any(User.class))).thenReturn(mockRequests);

        List<MasterRequest> result = masterRequestService.getAll();
        assertEquals(2, result.size());
        verify(masterRequestRepository, times(1)).findAllByUser(any(User.class));
    }

    @Test
    void save() {
        MasterRequest requestToSave = new MasterRequest();
        masterRequestService.save(requestToSave);
        verify(masterRequestRepository, times(1)).save(requestToSave);
    }

    @Test
    void deleteById() {
        long requestId = 1L;
        masterRequestService.deleteById(requestId);
        verify(masterRequestRepository, times(1)).deleteById(requestId);
    }
}