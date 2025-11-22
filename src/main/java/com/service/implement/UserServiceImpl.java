package com.service.implement;

import com.dao.UserDAO;
import com.dao.implement.UserDAOImpl;
import com.entity.User;
import com.service.UserService;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;


    public UserServiceImpl(EntityManager em) {
        this.userDAO = new UserDAOImpl(em);
    }

    @Override
    public User findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public List<User> findAll() {

        return userDAO.findAll()    ;
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(int id) {
        ;
    }

    @Override
    public void delete(String name) {
        userDAO.delete(name);
    }

    public User login(String email, String password) {
        User user= userDAO.findbyEmail(email);
        if (user!=null && password.equals(user.getPassword())) {
//            this.user=user;
            return user;
        }
        return null;
    }
}
