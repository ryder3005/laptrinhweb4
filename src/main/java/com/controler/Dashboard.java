package com.controler;

import com.entity.User;
import com.entity.Category;
import com.entity.Videos;
import com.service.implement.UserServiceImpl;
import com.service.implement.CategoryServiceImpl;
import com.service.implement.VideoServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/dashboard")
public class Dashboard extends HttpServlet {

    private final UserServiceImpl userService =null;
    private final CategoryServiceImpl categoryService = null;
    private final VideoServiceImpl videoService = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = (EntityManager) req.getAttribute("em");
        UserServiceImpl userService= new UserServiceImpl(em);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(em);
        VideoServiceImpl videoService = new VideoServiceImpl(em);
        // Lấy danh sách dữ liệu từ các service
        List<User> users = userService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Videos> videos = videoService.findAll();

        // Set dữ liệu vào request
        req.setAttribute("users", users);
        req.setAttribute("categories", categories);
        req.setAttribute("videos", videos);

        // Forward tới dashboard.jsp
        req.getRequestDispatcher("/views/admin/dashboard.jsp").forward(req, resp);
    }
}
