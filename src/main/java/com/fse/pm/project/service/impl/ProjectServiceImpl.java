package com.fse.pm.project.service.impl;

import com.fse.pm.project.dao.model.Project;
import com.fse.pm.project.dao.repository.ProjectRepository;
import com.fse.pm.project.presentation.model.request.ProjectRequest;
import com.fse.pm.project.presentation.model.response.ProjectResponse;
import com.fse.pm.project.service.ProjectService;
import com.fse.pm.user.dao.model.User;
import com.fse.pm.user.dao.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public ProjectServiceImpl(final ProjectRepository projectRepository, final UserRepository userRepository, final ModelMapper modelMapper){
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public ProjectResponse getProject(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        ProjectResponse projectResponse = modelMapper.map(project, ProjectResponse.class);
        User user = userRepository.findUserByProjectId(projectId);
        if (user != null) {
            projectResponse.setManagerId(user.getId());
            projectResponse.setManagerName(user.getFirstName() + user.getLastName());
        }
        return projectResponse;
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        List<ProjectResponse> projectResponses = new ArrayList<>();
        List<Project> projects = projectRepository.findAll();
        projects.forEach(project -> {
            ProjectResponse projectResponse = modelMapper.map(project, ProjectResponse.class);
            User user = userRepository.findUserByProjectId(project.getProjectId());
            if(user != null) {
                projectResponse.setManagerId(user.getId());
                projectResponse.setManagerName(user.getFirstName() + user.getLastName());
            }
            projectResponses.add(projectResponse);
        });
        return projectResponses;
    }

    @Override
    public void createProject(ProjectRequest projectRequest) {
        saveProject(projectRequest);
    }

    @Override
    public void updateProject(ProjectRequest projectRequest) {
        saveProject(projectRequest);
    }

    @Override
    public void deleteProject(Integer projectId) {
        User user = userRepository.findUserByProjectId(projectId);
        if(user != null) {
            user.setProjectId(null);
            userRepository.save(user);
        }
        projectRepository.deleteById(projectId);

    }
    private void saveProject(ProjectRequest projectRequest) {
        if(projectRequest.getProjectId() != null) {
            User user = userRepository.findUserByProjectId(projectRequest.getProjectId());
            user.setProjectId(null);
            userRepository.save(user);
        }
        Project project = projectRepository.save(modelMapper.map(projectRequest, Project.class));
        if(projectRequest.getManagerId() != null) {
            User user = userRepository.findById(projectRequest.getManagerId()).orElse(null);
            user.setProjectId(project.getProjectId());
            userRepository.save(user);
        }
    }
}
