package com.fse.pm.task.dao.model;

import javax.persistence.*;

@Entity
@Table(name="parent_task")
public class ParentTask {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer parentId;
    private String parentTask;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentTask() {
        return parentTask;
    }

    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }
}
