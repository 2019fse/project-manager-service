package com.fse.pm.user.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.pm.user.dao.model.User;
import com.fse.pm.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;

    @Test
    public void getUserList() throws Exception {
        List<User> users = Arrays.asList(getTestUser());
        given(userService.getAllUsers()).willReturn(users);
        mvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("First"));
    }

    @Test
    public void getUser() throws Exception {
        given(userService.getUser(anyInt())).willReturn(getTestUser());
        mvc.perform(get("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("First"));
    }

    @Test
    public void createUser()  throws Exception {
        doNothing().when(userService).createUser(any());
        mvc.perform(post("/api/user")
                .content(asJsonString(getTestUser()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser() throws Exception {
        doNothing().when(userService).createUser(any());
        mvc.perform(put("/api/user")
                .content(asJsonString(getTestUser()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUser() throws Exception {
        doNothing().when(userService).deleteUser(anyInt());
        mvc.perform(delete("/api/user/1")
                .content(asJsonString(getTestUser()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private User getTestUser() {
        User user = new User();
        user.setId(10);
        user.setFirstName("First");
        return user;
    }

}