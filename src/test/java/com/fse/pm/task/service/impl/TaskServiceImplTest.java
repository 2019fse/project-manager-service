package com.fse.pm.task.service.impl;

import com.fse.pm.project.dao.repository.ProjectRepository;
import com.fse.pm.task.dao.model.ParentTask;
import com.fse.pm.task.dao.model.Task;
import com.fse.pm.task.dao.repository.ParentTaskRepository;
import com.fse.pm.task.dao.repository.TaskRepository;
import com.fse.pm.task.presentation.model.response.TaskResponse;
import com.fse.pm.test.TestUtil;
import com.fse.pm.user.dao.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {
    @Mock
    private ParentTaskRepository parentTaskRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Spy
    private ModelMapper modelMapper;
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
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getTestUser()));
        when(userRepository.save(any())).thenReturn(TestUtil.getTestUser());
        when(userRepository.findUserByTaskId(anyInt())).thenReturn(TestUtil.getTestUser());
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
        when(taskRepository.findTasksByProjectId(anyInt())).thenReturn(Arrays.asList(TestUtil.getTestTask()));
        when(userRepository.findUserByTaskId(anyInt())).thenReturn(TestUtil.getTestUser());
        when(parentTaskRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getTestParentTask()));
        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getTestProject()));
        List<TaskResponse> tasks = taskService.getAllTasksForProject(10);
        assertEquals(1, tasks.size());
    }

    @Test
    public void updateTask() {
        when(taskRepository.save(any())).thenReturn(TestUtil.getTestTask());
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getTestUser()));
        when(userRepository.findUserByTaskId(anyInt())).thenReturn(TestUtil.getTestUser());
        when(userRepository.save(any())).thenReturn(TestUtil.getTestUser());
        taskService.updateTask(TestUtil.getTestTask());
        verify(taskRepository, times(1)).save(any());
    }

    @Test
    public void updateTaskUserNull() {
        when(taskRepository.save(any())).thenReturn(TestUtil.getTestTask());
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getTestUser()));
        when(userRepository.findUserByTaskId(anyInt())).thenReturn(null);
        when(userRepository.save(any())).thenReturn(TestUtil.getTestUser());
        taskService.updateTask(TestUtil.getTestTask());
        verify(taskRepository, times(1)).save(any());
    }
    @Test
    public void updateTaskNull() {
        Task task = TestUtil.getTestTask();
        task.setTaskId(null);
        task.setUserId(null);
        when(taskRepository.save(any())).thenReturn(task);
        taskService.updateTask(TestUtil.getTestTask());
        verify(taskRepository, times(1)).save(any());
    }

    @Test
    public void getAllTasksNull() {
        Task task = TestUtil.getTestTask();
        task.setProjectId(null);
        task.setParentId(null);
        when(taskRepository.findTasksByProjectId(anyInt())).thenReturn(Arrays.asList(task));
        when(userRepository.findUserByTaskId(anyInt())).thenReturn(null);
        List<TaskResponse> tasks = taskService.getAllTasksForProject(10);
        assertEquals(1, tasks.size());
    }
}