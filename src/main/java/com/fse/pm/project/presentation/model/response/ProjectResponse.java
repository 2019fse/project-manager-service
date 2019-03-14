package com.fse.pm.project.presentation.model.response;

import java.time.LocalDate;

public class ProjectResponse {
    private Integer projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer priority;
    private Integer managerId;
    private String managerName;
    private Long taskTotalCount = 0L;
    private Long taskCompletedCount = 0L;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getTaskTotalCount() {
        return taskTotalCount;
    }

    public void setTaskTotalCount(Long taskTotalCount) {
        this.taskTotalCount = taskTotalCount;
    }

    public Long getTaskCompletedCount() {
        return taskCompletedCount;
    }

    public void setTaskCompletedCount(Long taskCompletedCount) {
        this.taskCompletedCount = taskCompletedCount;
    }
}
