package com.fse.pm.user.service.impl;

import com.fse.pm.user.dao.model.User;
import com.fse.pm.user.dao.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void getUser() {
        User expectedUser = new User();
        expectedUser.setId(10);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(expectedUser));
        User actualUser = userService.getUser(10);
        assertEquals(expectedUser.getId(), actualUser.getId());
    }

    @Test
    public void getUserNull() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        User actualUser = userService.getUser(10);
        assertNull(actualUser);
    }

    @Test
    public void getAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User()));
        List<User> users = userService.getAllUsers();
        assertEquals(1, users.size());
    }

    @Test
    public void createUser() {
        when(userRepository.save(any())).thenReturn(new User());
        userService.createUser(new User());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void updateUser() {
        when(userRepository.save(any())).thenReturn(new User());
        userService.updateUser(new User());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void deleteUser() {
        doNothing().when(userRepository).deleteById(anyInt());
        userService.deleteUser(0);
        verify(userRepository, times(1)).deleteById(anyInt());
    }
}