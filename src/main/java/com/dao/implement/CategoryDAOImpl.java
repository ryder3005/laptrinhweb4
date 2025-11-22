package com.dao.implement;

import com.config.JpaConfig;
import com.dao.CategoryDAO;
import com.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private EntityManager entityManager = null;

    @Override
    public List<Category> findAll() {
        String jpql = "SELECT c FROM Category c";
        return entityManager.createQuery(jpql, Category.class)
                .getResultList();
    }

    @Override
    public Category findById(int id) {
        return entityManager.find(Category.class, id);
    }


    @Override
    public void save(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(category);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close(); // đóng được vì là EntityManager local
        }
    }


    @Override
    public void update(Category category) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.merge(category);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    public CategoryDAOImpl(EntityManager em ) {
        this.entityManager=em;
    }

    @Override
    public void delete(int id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();

            Category category = entityManager.find(Category.class, id);
            if (category != null) {
                entityManager.remove(category);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

}