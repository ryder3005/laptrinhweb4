package com.dao;

import com.entity.Category;
import com.entity.User;

import java.util.List;

public interface UserDAO {
    User findById(int id);
    User findByName(String name);
    List<User> findAll();

    void save(User user);
    void update(User user);
    void delete(String username);
    User findbyEmail(String email);
}
