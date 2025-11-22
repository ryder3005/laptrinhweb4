package com.controler;

import com.entity.Category;
import com.entity.User;
import com.service.implement.CategoryServiceImpl;
import com.service.implement.UserServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/categories/*")
public class CategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        EntityManager em = (EntityManager) req.getAttribute("em");
        CategoryServiceImpl categoryService = new CategoryServiceImpl(em);
        UserServiceImpl userService = new UserServiceImpl(em);

        String action = req.getPathInfo();

        if (action == null || action.equals("/")) {
            List<Category> categories = categoryService.findAll();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/admin/categories/list.jsp").forward(req, resp);

        } else if (action.equals("/edit")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Category category = categoryService.findById(id);
                List<User> users = userService.findAll();
                req.setAttribute("category", category);
                req.setAttribute("users", users);
                req.getRequestDispatcher("/views/admin/categories/edit.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing category id");
            }

        } else if (action.equals("/delete")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Category category = categoryService.findById(id);
                if (category != null) {
                    categoryService.delete(category.getCategoryName());
                }
            }
            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        } else if (action.equals("/create")) {
            List<User> users = userService.findAll();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/views/admin/categories/create.jsp").forward(req, resp);

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        EntityManager em = (EntityManager) req.getAttribute("em");
        CategoryServiceImpl categoryService = new CategoryServiceImpl(em);
        UserServiceImpl userService = new UserServiceImpl(em);

        String action = req.getPathInfo();
        String name = req.getParameter("name");
        String code = req.getParameter("categorycode");
        String images = req.getParameter("images");
        boolean status = req.getParameter("status") != null;
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = userService.findByName(String.valueOf(userId)); // nếu dùng findById thì sửa lại

        if (name == null || name.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category name cannot be empty");
            return;
        }

        Category category = new Category();
        category.setCategoryName(name);
        category.setCategorycode(code);
        category.setImages(images);
        category.setStatus(status);
        category.setUser(user);

        if (action.equals("/create")) {
            categoryService.save(category);

        } else if (action.equals("/edit")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                category.setCategoryId(id);
                categoryService.update(category);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }
}
