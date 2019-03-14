package com.fse.pm.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.pm.project.dao.model.Project;
import com.fse.pm.project.presentation.model.request.ProjectRequest;
import com.fse.pm.project.presentation.model.response.ProjectResponse;
import com.fse.pm.task.dao.model.ParentTask;
import com.fse.pm.task.dao.model.Task;
import com.fse.pm.task.presentation.model.response.TaskResponse;
import com.fse.pm.user.dao.model.User;

import java.time.LocalDate;

public class TestUtil {
    public static ProjectResponse getTestProjectResponse() {
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setProjectId(1);
        projectResponse.setProjectName("Test Project");
        projectResponse.setManagerName("ManagerName");
        projectResponse.setManagerId(1);
        projectResponse.setEndDate(LocalDate.now());
        projectResponse.setStartDate(LocalDate.now());
        projectResponse.setTaskCompletedCount(1L);
        projectResponse.setTaskTotalCount(1L);
        projectResponse.setPriority(10);
        return projectResponse;
    }

    public static ProjectRequest getTestProjectRequest() {
        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setProjectId(1);
        projectRequest.setProjectName("Test Project");
        projectRequest.setManagerId(10);
        return projectRequest;
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static User getTestUser() {
        User user = new User();
        user.setId(10);
        user.setFirstName("First");
        user.setProjectId(1);
        return user;
    }

    public static Project getTestProject() {
        Project project = new Project();
        project.setProjectId(1);
        project.setProjectName("Test project");
        return project;
    }

    public static ParentTask getTestParentTask() {
        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("Test Parent Task");
        return parentTask;
    }

    public static Task getTestTask() {
        Task task = new Task();
        task.setTaskName("Test Task");
        task.setUserId(10);
        task.setParentId(10);
        task.setProjectId(10);
        task.setTaskId(10);
        return task;
    }

    public static TaskResponse getTestTaskResponse() {
        TaskResponse task = new TaskResponse();
        task.setTaskName("Test Task");
        task.setUserId(10);
        task.setParentId(10);
        task.setPriority(0);
        task.setProjectName("");
        return task;
    }
}
