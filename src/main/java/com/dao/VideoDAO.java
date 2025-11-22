package com.dao;

import com.entity.Videos;

import java.util.List;

public interface VideoDAO {
    Videos findById(int id);

    List<Videos> searchByTitle(String keyword);

    List<Videos> findAll();
    void save(Videos videos);
    void update(Videos videos);
    void delete(Videos videos);

}
