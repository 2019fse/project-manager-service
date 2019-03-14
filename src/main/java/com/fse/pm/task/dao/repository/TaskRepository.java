package com.fse.pm.task.dao.repository;

import com.fse.pm.task.dao.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t WHERE t.projectId = ?1")
    List<Task> findTasksByProjectId(Integer projectId);
}
