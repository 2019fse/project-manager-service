package com.fse.pm.user.dao.repository;

import com.fse.pm.user.dao.model.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.projectId = ?1")
    User findUserByProjectId(Integer projectId);
    @Query("SELECT u FROM User u WHERE u.taskId = ?1")
    User findUserByTaskId(Integer taskId);
}
