package com.fse.pm.task.service.impl;

import com.fse.pm.project.dao.model.Project;
import com.fse.pm.project.dao.repository.ProjectRepository;
import com.fse.pm.task.dao.model.ParentTask;
import com.fse.pm.task.dao.model.Task;
import com.fse.pm.task.dao.repository.ParentTaskRepository;
import com.fse.pm.task.dao.repository.TaskRepository;
import com.fse.pm.task.presentation.model.response.TaskResponse;
import com.fse.pm.task.service.TaskService;
import com.fse.pm.user.dao.model.User;
import com.fse.pm.user.dao.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final ParentTaskRepository parentTaskRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(final ParentTaskRepository parentTaskRepository,
                           final TaskRepository taskRepository,
                           final UserRepository userRepository,
                           final ProjectRepository projectRepository,
                           final ModelMapper modelMapper) {
        this.parentTaskRepository = parentTaskRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository =  projectRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public void createParentTask(ParentTask parentTask) {
        parentTaskRepository.save(parentTask);
    }

    @Override
    public void createTask(Task task) {
        saveTask(task);
    }

    @Override
    public void updateTask(Task task) {
        saveTask(task);
    }
    private void saveTask(Task task) {
        if(task.getTaskId() != null) {
            User user = userRepository.findUserByTaskId(task.getTaskId());
            if(user != null) {
                user.setProjectId(null);
                userRepository.save(user);
            }
        }
        taskRepository.save(task);
        if(task.getUserId() != null) {
            Optional<User> user = userRepository.findById(task.getUserId());
            user.ifPresent(u -> {
                u.setTaskId(task.getTaskId());
                userRepository.save(u);
            });
        }
    }

    @Override
    public List<ParentTask> getAllParentTasks() {
        return parentTaskRepository.findAll();
    }

    @Override
    public List<TaskResponse> getAllTasksForProject(Integer projectId) {
        List<Task> tasks = taskRepository.findTasksByProjectId(projectId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasks.forEach(task -> {
            TaskResponse taskResponse = modelMapper.map(task, TaskResponse.class);
            User user = userRepository.findUserByTaskId(task.getTaskId());
            if(user != null) {
                taskResponse.setUserId(user.getId());
                taskResponse.setUserName(user.getFirstName() + user.getLastName());
            }
            if(task.getParentId() != null) {
                Optional<ParentTask> parentTask = parentTaskRepository.findById(task.getParentId());
                parentTask.ifPresent(p -> {
                    taskResponse.setParentId(p.getParentId());
                    taskResponse.setParentTask(p.getParentTask());
                });
            }
            if(task.getProjectId() != null) {
                Optional<Project> parentTask = projectRepository.findById(task.getProjectId());
                parentTask.ifPresent(p -> {
                    taskResponse.setProjectId(p.getProjectId());
                    taskResponse.setProjectName(p.getProjectName());
                });
            }
            taskResponses.add(taskResponse);
        });
        return taskResponses;
    }
}
