package com.fse.pm.project.presentation.controller;

import com.fse.pm.project.presentation.model.request.ProjectRequest;
import com.fse.pm.project.presentation.model.response.ProjectResponse;
import com.fse.pm.project.service.ProjectService;
import com.fse.pm.test.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ProjectService projectService;

    @Test
    public void getProjectList() throws Exception {
        List<ProjectResponse> projects = Arrays.asList(TestUtil.getTestProjectResponse());
        given(projectService.getAllProjects()).willReturn(projects);
        mvc.perform(get("/api/project")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].projectName").value("Test Project"));
    }

    @Test
    public void getProject() throws Exception {
        given(projectService.getProject(anyInt())).willReturn(TestUtil.getTestProjectResponse());
        mvc.perform(get("/api/project/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("projectName").value("Test Project"));
    }

    @Test
    public void createProject() throws Exception {
        doNothing().when(projectService).createProject(any());
        mvc.perform(post("/api/project")
                .content(TestUtil.asJsonString(TestUtil.getTestProjectRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateProject() throws Exception {
        doNothing().when(projectService).createProject(any());
        mvc.perform(put("/api/project")
                .content(TestUtil.asJsonString(TestUtil.getTestProjectRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteProject() throws Exception {
        doNothing().when(projectService).deleteProject(anyInt());
        mvc.perform(delete("/api/project/1")
                .content(TestUtil.asJsonString(TestUtil.getTestProjectRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}