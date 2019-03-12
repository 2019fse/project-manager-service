package com.fse.pm.task.presentation.controller;

import com.fse.pm.task.dao.model.ParentTask;
import com.fse.pm.task.dao.model.Task;
import com.fse.pm.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/api/parenttask")
    public void createParentTask(@RequestBody ParentTask task) {
        taskService.createParentTask(task);
    }

    @PostMapping("/api/task")
    public void createTask(@RequestBody Task task) {
        taskService.createTask(task);
    }

}
