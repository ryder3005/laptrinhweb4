package com.service;

import com.entity.Category;

import java.util.List;

public interface CategoryService  {
    List<Category> findAll();
    void save(Category category);
    void update(Category category);
    void delete(String name);

    void delete(int id);

    Category findById(int id);
}
