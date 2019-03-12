package com.fse.pm.task.service;

import com.fse.pm.task.dao.model.ParentTask;
import com.fse.pm.task.dao.model.Task;

public interface TaskService {
    void createParentTask(ParentTask parentTask);
    void createTask(Task task);
}
