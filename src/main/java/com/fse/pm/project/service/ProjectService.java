package com.fse.pm.project.service;

import com.fse.pm.project.dao.model.Project;
import com.fse.pm.project.presentation.model.request.ProjectRequest;
import com.fse.pm.project.presentation.model.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse getProject(Integer projectId);
    List<ProjectResponse> getAllProjects();
    Project createProject(ProjectRequest projectRequest);
    void updateProject(ProjectRequest projectRequest);
    void deleteProject(Integer projectId);
}
