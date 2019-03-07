package com.fse.pm.user.service;

import com.fse.pm.user.dao.model.User;

import java.util.List;

public interface UserService {
    User getUser(Integer userId);
    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Integer userId);
}
