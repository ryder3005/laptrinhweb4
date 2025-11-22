package com.controler;

import com.entity.Videos;
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
                req.setAttribute("video", video);
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

        String action = req.getPathInfo();
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        Videos video = new Videos();
        video.setTitle(title);
        video.setDescription(description);

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
