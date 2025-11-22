package com.dao;


import com.entity.Category;
import com.entity.User;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    Category findById(int id);
    void save(Category category);
    void update(Category category);


    void delete(int id);
}
