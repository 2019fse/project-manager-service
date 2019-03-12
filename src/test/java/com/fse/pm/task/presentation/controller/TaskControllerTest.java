package com.fse.pm.task.presentation.controller;

import com.fse.pm.task.service.TaskService;
import com.fse.pm.test.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private TaskService taskService;
    @Test
    public void createParentTask() throws Exception {
        doNothing().when(taskService).createParentTask(any());
        mvc.perform(post("/api/parenttask")
                .content(TestUtil.asJsonString(TestUtil.getTestParentTask()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createTask() throws Exception {
        doNothing().when(taskService).createTask(any());
        mvc.perform(post("/api/task")
                .content(TestUtil.asJsonString(TestUtil.getTestTask()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}