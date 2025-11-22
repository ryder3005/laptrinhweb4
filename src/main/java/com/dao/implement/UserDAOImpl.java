package com.dao.implement;

import com.config.JpaConfig;
import com.dao.UserDAO;
import com.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final EntityManager em;

    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByName(String name) {
        String jpql = "SELECT u FROM User u WHERE u.username = :name";
        List<User> results = em.createQuery(jpql, User.class)
                .setParameter("name", name)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<User> findAll() {
        String jpql = "SELECT u FROM User u";
        return em.createQuery(jpql, User.class).getResultList();
    }

    @Override
    public void save(User user) {
        em.persist(user);  // Transaction đã được begin ở Filter
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(String username) {
        User user = findByName(username);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public User findbyEmail(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        List<User> results = em.createQuery(jpql, User.class)
                .setParameter("email", email)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
