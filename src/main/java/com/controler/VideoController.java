package com.controler;

import com.entity.Category;
import com.entity.Videos;
import com.service.implement.CategoryServiceImpl;
import com.service.implement.VideoServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/videos/*")
public class VideoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        EntityManager em = (EntityManager) req.getAttribute("em");
        VideoServiceImpl videoService = new VideoServiceImpl(em);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(em);

        String action = req.getPathInfo();

        if (action == null || action.equals("/")) {
            List<Videos> videos = videoService.findAll();
            req.setAttribute("videos", videos);
            req.getRequestDispatcher("/views/admin/videos/list.jsp").forward(req, resp);

        } else if (action.equals("/edit")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Videos video = videoService.findById(id);
                List<Category> categories = categoryService.findAll();
                req.setAttribute("video", video);
                req.setAttribute("categories", categories);
                req.getRequestDispatcher("/views/admin/videos/edit.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing video id");
            }

        } else if (action.equals("/delete")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Videos video = videoService.findById(id);
                if (video != null) {
                    videoService.delete(video);
                }
            }
            resp.sendRedirect(req.getContextPath() + "/admin/videos");

        } else if (action.equals("/create")) {
            List<Category> categories = categoryService.findAll();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/admin/videos/create.jsp").forward(req, resp);

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        EntityManager em = (EntityManager) req.getAttribute("em");
        VideoServiceImpl videoService = new VideoServiceImpl(em);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(em);

        String action = req.getPathInfo();

        String title = req.getParameter("title");
        String poster = req.getParameter("poster");
        String description = req.getParameter("description");
        int views = 0;
        try {
            views = Integer.parseInt(req.getParameter("views"));
        } catch (NumberFormatException ignored) {}

        boolean active = req.getParameter("active") != null;
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Category category = categoryService.findById(categoryId);

        Videos video = new Videos();
        video.setTitle(title);
        video.setPoster(poster);
        video.setDescription(description);
        video.setViews(views);
        video.setActive(active);
        video.setCategory(category);

        if (action.equals("/create")) {
            videoService.save(video);

        } else if (action.equals("/edit")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                video.setVideoId(id);
                videoService.update(video);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/admin/videos");
    }
}
