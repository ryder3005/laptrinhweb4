package com.controler;

import com.entity.User;
import com.service.implement.UserServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/users/*")
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        EntityManager em = (EntityManager) req.getAttribute("em");
        UserServiceImpl userService = new UserServiceImpl(em);

        String action = req.getPathInfo();
        if (action == null || action.equals("/")) {
            req.setAttribute("users", userService.findAll());
            req.getRequestDispatcher("/views/admin/users/list.jsp").forward(req, resp);
        } else if (action.equals("/edit")) {
            String username = req.getParameter("username");
            User user = userService.findByName(username);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/views/admin/users/edit.jsp").forward(req, resp);
        } else if (action.equals("/delete")) {
            String username = req.getParameter("username");
            userService.delete(username);
            resp.sendRedirect(req.getContextPath() + "/admin/users");
        } else if (action.equals("/create")) {
            req.getRequestDispatcher("/views/admin/users/create.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        EntityManager em = (EntityManager) req.getAttribute("em");
        UserServiceImpl userService = new UserServiceImpl(em);

        String action = req.getPathInfo();
        if (action.equals("/create") || action.equals("/edit")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            if (action.equals("/create")) {
                userService.save(user);
            } else {
                userService.update(user);
            }

            resp.sendRedirect(req.getContextPath() + "/admin/users");
        }
    }
}
