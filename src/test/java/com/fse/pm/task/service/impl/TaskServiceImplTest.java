package com.fse.pm.task.service.impl;

import com.fse.pm.task.dao.model.ParentTask;
import com.fse.pm.task.dao.model.Task;
import com.fse.pm.task.dao.repository.ParentTaskRepository;
import com.fse.pm.task.dao.repository.TaskRepository;
import com.fse.pm.test.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {
    @Mock
    private ParentTaskRepository parentTaskRepository;
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void createParentTask() {
        when(parentTaskRepository.save(any())).thenReturn(TestUtil.getTestParentTask());
        taskService.createParentTask(TestUtil.getTestParentTask());
        verify(parentTaskRepository, times(1)).save(any());
    }

    @Test
    public void createTask() {
        when(taskRepository.save(any())).thenReturn(TestUtil.getTestTask());
        taskService.createTask(TestUtil.getTestTask());
        verify(taskRepository, times(1)).save(any());
    }

    @Test
    public void getAllParentTasks() {
        when(parentTaskRepository.findAll()).thenReturn(Arrays.asList(new ParentTask()));
        List<ParentTask> tasks = taskService.getAllParentTasks();
        assertEquals(1, tasks.size());
    }

    @Test
    public void getAllTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(new Task()));
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
    }
}