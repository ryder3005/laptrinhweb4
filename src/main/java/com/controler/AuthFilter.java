package com.controler;

import jakarta.servlet.Filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
public class AuthFilter implements Filter {



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String uri = req.getRequestURI();

        // Nếu chưa login, chuyển về login page
        if (session == null || session.getAttribute("roleId") == null) {
            res.sendRedirect(req.getContextPath() + "views/login.jsp");
            return;
        }

        // Kiểm tra role
        int roleId = (int) session.getAttribute("roleId");
        if (uri.startsWith("/admin") && roleId != 3) {
            res.sendRedirect(req.getContextPath() + "views/access-denied.jsp");
            return;
        }
        if (uri.startsWith("/manager") && roleId != 2) {
            res.sendRedirect(req.getContextPath() + "views/access-denied.jsp");
            return;
        }
        if (uri.startsWith("/user") && roleId != 1) {
            res.sendRedirect(req.getContextPath() + "views/access-denied.jsp");
            return;
        }

        // Nếu hợp lệ, cho request đi tiếp
        chain.doFilter(request, response);
    }

}
