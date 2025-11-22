package com.service;

import com.entity.User;
import java.util.List;

public interface UserService {
    User findByName(String  name);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(int id);

    void delete(String name);
}
