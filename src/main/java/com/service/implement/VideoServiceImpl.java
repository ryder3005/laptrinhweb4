package com.service.implement;

import com.dao.VideoDAO;
import com.dao.implement.VideoDAOImpl;
import com.entity.Videos;
import com.service.VideoService;
import jakarta.persistence.EntityManager;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    VideoDAO videoDAO= null;

    public VideoServiceImpl(EntityManager em) {
        this.videoDAO = new VideoDAOImpl(em);
    }

    @Override
    public Videos findById(int id) {
        return videoDAO.findById(id);
    }

    @Override
    public List<Videos> searchByTitle(String keyword) {
        return videoDAO.searchByTitle(keyword);
    }

    @Override
    public List<Videos> findAll() {
        return videoDAO.findAll();
    }

    @Override
    public void save(Videos videos) {
        videoDAO.save(videos);
    }

    @Override
    public void update(Videos videos) {
        videoDAO.update(videos);
    }

    @Override
    public void delete(Videos videos) {
        videoDAO.delete(videos);
    }
}
