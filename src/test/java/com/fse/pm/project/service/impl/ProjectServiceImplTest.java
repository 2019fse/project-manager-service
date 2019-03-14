package com.fse.pm.project.service.impl;

import com.fse.pm.project.dao.model.Project;
import com.fse.pm.project.dao.repository.ProjectRepository;
import com.fse.pm.project.presentation.model.request.ProjectRequest;
import com.fse.pm.project.presentation.model.response.ProjectResponse;
import com.fse.pm.task.dao.repository.TaskRepository;
import com.fse.pm.test.TestUtil;
import com.fse.pm.user.dao.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private TaskRepository taskRepository;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    public void getProject() {
        Project expectedProject = TestUtil.getTestProject();
        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(expectedProject));
        when(userRepository.findUserByProjectId(anyInt())).thenReturn(TestUtil.getTestUser());
        ProjectResponse actualProjectResponse = projectService.getProject(1);
        assertEquals(actualProjectResponse.getProjectId(), expectedProject.getProjectId());
    }

    @Test
    public void getAllProjects() {
        List<Project> expectedProjectList = Arrays.asList(TestUtil.getTestProject());
        when(projectRepository.findAll()).thenReturn(expectedProjectList);
        when(userRepository.findUserByProjectId(anyInt())).thenReturn(TestUtil.getTestUser());
        when(taskRepository.findTasksByProjectId(anyInt())).thenReturn(Arrays.asList(TestUtil.getTestTask()));
        List<ProjectResponse> actualProjectResponseList = projectService.getAllProjects();
        assertEquals(actualProjectResponseList.get(0).getProjectId(), expectedProjectList.get(0).getProjectId());
    }

    @Test
    public void createProject() {
        ProjectRequest projectRequest = TestUtil.getTestProjectRequest();
        projectRequest.setProjectId(null);
        when(projectRepository.save(any())).thenReturn(TestUtil.getTestProject());
        when(userRepository.save(any())).thenReturn(TestUtil.getTestUser());
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getTestUser()));
        projectService.createProject(projectRequest);
        verify(projectRepository, times(1)).save(any());
        verify(userRepository, times(1)).findById(anyInt());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void updateProject() {
        ProjectRequest projectRequest = TestUtil.getTestProjectRequest();
        when(projectRepository.save(any())).thenReturn(TestUtil.getTestProject());
        when(userRepository.save(any())).thenReturn(TestUtil.getTestUser());
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getTestUser()));
        when(userRepository.findUserByProjectId(anyInt())).thenReturn(TestUtil.getTestUser());
        projectService.updateProject(projectRequest);
        verify(projectRepository, times(1)).save(any());
        verify(userRepository, times(1)).findById(anyInt());
        verify(userRepository, times(2)).save(any());
    }

    @Test
    public void deleteProject() {
        when(userRepository.findUserByProjectId(anyInt())).thenReturn(TestUtil.getTestUser());
        when(userRepository.save(any())).thenReturn(TestUtil.getTestUser());
        doNothing().when(projectRepository).deleteById(anyInt());
        projectService.deleteProject(1);
        verify(userRepository, times(1)).save(any());
        verify(userRepository, times(1)).findUserByProjectId(any());
        verify(projectRepository, times(1)).deleteById(any());
    }

    @Test
    public void deleteProjectNullUser() {
        when(userRepository.findUserByProjectId(anyInt())).thenReturn(null);
        doNothing().when(projectRepository).deleteById(anyInt());
        projectService.deleteProject(1);
        verify(userRepository, times(1)).findUserByProjectId(any());
        verify(projectRepository, times(1)).deleteById(any());
    }

    @Test
    public void updateProjectNullUser() {
        ProjectRequest projectRequest = TestUtil.getTestProjectRequest();
        projectRequest.setManagerId(null);
        when(projectRepository.save(any())).thenReturn(TestUtil.getTestProject());
        when(userRepository.save(any())).thenReturn(TestUtil.getTestUser());
        when(userRepository.findUserByProjectId(anyInt())).thenReturn(TestUtil.getTestUser());
        projectService.updateProject(projectRequest);
        verify(projectRepository, times(1)).save(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void getAllProjectsNullUser() {
        List<Project> expectedProjectList = Arrays.asList(TestUtil.getTestProject());
        when(projectRepository.findAll()).thenReturn(expectedProjectList);
        when(userRepository.findUserByProjectId(anyInt())).thenReturn(null);
        when(taskRepository.findTasksByProjectId(anyInt())).thenReturn(Arrays.asList(TestUtil.getTestTask()));
        List<ProjectResponse> actualProjectResponseList = projectService.getAllProjects();
        assertEquals(actualProjectResponseList.get(0).getProjectId(), expectedProjectList.get(0).getProjectId());
    }

    @Test
    public void getProjectNullUser() {
        Project expectedProject = TestUtil.getTestProject();
        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(expectedProject));
        when(userRepository.findUserByProjectId(anyInt())).thenReturn(null);
        ProjectResponse actualProjectResponse = projectService.getProject(1);
        assertEquals(actualProjectResponse.getProjectId(), expectedProject.getProjectId());
    }
}