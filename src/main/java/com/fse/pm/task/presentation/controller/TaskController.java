package com.fse.pm.task.presentation.controller;

import com.fse.pm.task.dao.model.ParentTask;
import com.fse.pm.task.dao.model.Task;
import com.fse.pm.task.presentation.model.response.TaskResponse;
import com.fse.pm.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/api/parenttask")
    public List<ParentTask> getAllParentTasks() {
        return taskService.getAllParentTasks();
    }

    @GetMapping("/api/task/project/{projectId}")
    public List<TaskResponse> getAllTasksForProject(@PathVariable Integer projectId) {
        return taskService.getAllTasksForProject(projectId);
    }
    @PutMapping("/api/task")
    public void updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
    }

    @DeleteMapping("/api/task/{taskId}")
    public void deleteTask(@PathVariable Integer taskId){
        taskService.deleteTask(taskId);
    }

}
