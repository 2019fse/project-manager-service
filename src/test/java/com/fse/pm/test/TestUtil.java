package com.fse.pm.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.pm.project.dao.model.Project;
import com.fse.pm.project.presentation.model.request.ProjectRequest;
import com.fse.pm.project.presentation.model.response.ProjectResponse;
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
        projectResponse.setTaskCompletedCount(1);
        projectResponse.setTaskTotalCount(1);
        projectResponse.setPriority(10);
        return projectResponse;
    }

    public static ProjectRequest getTestProjectRequest() {
        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setProjectId(1);
        projectRequest.setProjectName("Test Project");
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
}
