package com.fse.pm.task.service;

import com.fse.pm.task.dao.model.ParentTask;
import com.fse.pm.task.dao.model.Task;
import com.fse.pm.task.presentation.model.response.TaskResponse;

import java.util.List;

public interface TaskService {
    void createParentTask(ParentTask parentTask);
    void createTask(Task task);
    void updateTask(Task task);
    List<ParentTask> getAllParentTasks();
    List<TaskResponse> getAllTasksForProject(Integer projectId);
}
