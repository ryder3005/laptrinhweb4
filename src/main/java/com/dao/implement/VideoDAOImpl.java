package com.dao.implement;

import com.dao.VideoDAO;
import com.entity.Videos;
import jakarta.persistence.EntityManager;

import java.util.List;

public class VideoDAOImpl implements VideoDAO {

    private final EntityManager em;

    public VideoDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Videos findById(int id) {
        return em.find(Videos.class, id);
    }

    @Override
    public List<Videos> searchByTitle(String keyword) {
        String jpql = "SELECT v FROM Videos v WHERE v.title LIKE :keyword";
        return em.createQuery(jpql, Videos.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

    @Override
    public List<Videos> findAll() {
        return em.createQuery("SELECT v FROM Videos v", Videos.class)
                .getResultList();
    }

    @Override
    public void save(Videos videos) {
        em.persist(videos); // Transaction đã được Filter quản lý
    }

    @Override
    public void update(Videos videos) {
        em.merge(videos);
    }

    @Override
    public void delete(Videos videos) {
        Videos merged = em.merge(videos); // merge nếu entity detached
        em.remove(merged);
    }
}
