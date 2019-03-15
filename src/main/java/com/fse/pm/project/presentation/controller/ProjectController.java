package com.fse.pm.project.presentation.controller;

import com.fse.pm.project.dao.model.Project;
import com.fse.pm.project.presentation.model.request.ProjectRequest;
import com.fse.pm.project.presentation.model.response.ProjectResponse;
import com.fse.pm.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;
    @Autowired
    public ProjectController(final ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponse> getProjectList() {
        return projectService.getAllProjects();
    }
    @GetMapping("/{projectId}")
    public ProjectResponse getProject(@PathVariable Integer projectId) {
        return projectService.getProject(projectId);
    }
    @PostMapping
    public Project createProject(@RequestBody ProjectRequest projectRequest) {
        return projectService.createProject(projectRequest);
    }

    @PutMapping
    public void updateProject(@RequestBody ProjectRequest projectRequest) {
        projectService.updateProject(projectRequest);
    }
    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Integer projectId) {
        projectService.deleteProject(projectId);
    }
}
