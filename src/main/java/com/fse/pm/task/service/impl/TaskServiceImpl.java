package com.fse.pm.task.service.impl;

import com.fse.pm.task.dao.model.ParentTask;
import com.fse.pm.task.dao.model.Task;
import com.fse.pm.task.dao.repository.ParentTaskRepository;
import com.fse.pm.task.dao.repository.TaskRepository;
import com.fse.pm.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final ParentTaskRepository parentTaskRepository;
    private final TaskRepository taskRepository;
    @Autowired
    public TaskServiceImpl(final ParentTaskRepository parentTaskRepository, final TaskRepository taskRepository) {
        this.parentTaskRepository = parentTaskRepository;
        this.taskRepository = taskRepository;
    }
    @Override
    public void createParentTask(ParentTask parentTask) {
        parentTaskRepository.save(parentTask);
    }

    @Override
    public void createTask(Task task) {
        taskRepository.save(task);
    }
}
