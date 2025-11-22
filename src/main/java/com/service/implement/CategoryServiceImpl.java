package com.service.implement;

import com.dao.CategoryDAO;
import com.dao.implement.CategoryDAOImpl;
import com.entity.Category;
import com.service.CategoryService;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDAO categoryDAO=null;

    public CategoryServiceImpl(EntityManager em) {
        this.categoryDAO = new CategoryDAOImpl(em);
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public void save(Category category) {
        categoryDAO.save(category);
    }

    @Override
    public void update(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public void delete(String name) {

    }

    @Override
    public void delete(int id) {
        categoryDAO.delete(id);
    }

    @Override
    public Category findById(int id) {

        return categoryDAO.findById(id);
    }
}
