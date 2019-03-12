package com.fse.pm.task.dao.repository;
import com.fse.pm.task.dao.model.ParentTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentTaskRepository extends  JpaRepository<ParentTask, Integer> {
}
