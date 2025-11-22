package com.controler;

import com.entity.User;
import com.service.implement.UserServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        if (session != null &&session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath()+ "/waiting");
            return;
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("account")) {
                    session=req.getSession(true);
                    session.setAttribute("account", cookie.getValue());
                    resp.sendRedirect(req.getContextPath()+ "/waiting");
                    return;
                }
            }
        }
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String email=req.getParameter("email");

        String password=req.getParameter("password");
        boolean isRememberMe = false;

        boolean remember=Boolean.parseBoolean(req.getParameter("remember"));
        if (req.getParameter("remember") != null) { isRememberMe = true; }
        String alertMsg="";
        if (email.isEmpty() || password.isEmpty()){
            alertMsg="Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("views/login.jsp").forward(req, resp);
            return;
        }
        EntityManager em = (EntityManager) req.getAttribute("em");
        UserServiceImpl userService=new UserServiceImpl(em);
        try {
            User user =userService.login(email,password);
            if  (user==null){
                alertMsg = "Tài khoản hoặc mật khẩu không đúng";
                req.setAttribute("alert", alertMsg);
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);        }
            else{
                HttpSession session = req.getSession(true);
                session.setAttribute("account", user);
                if (isRememberMe){
                    saveRemeberMe(resp, email);
                }
                boolean roleId=user.getActive();
                session.setAttribute("roleId",roleId);
                req.setAttribute("message","xin chao" + user.getUsername());
                // Chuyển hướng về trang home hoặc dashboard
                System.out.println("xin chao"+user.getUsername()+" roleId"+roleId);
                if (!roleId){
                    resp.sendRedirect(req.getContextPath() + "/user/home");
                }else{
                    resp.sendRedirect(req.getContextPath() + "/admin/home");
                }


            }
        }catch (Exception e){
            resp.sendRedirect(req.getContextPath() + "views/access-denied.jsp");
        }


    }
    private void saveRemeberMe(HttpServletResponse response, String
            username){
        Cookie cookie = new Cookie(COOKIE_REMEMBER,
                username);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }
    public static final String COOKIE_REMEMBER = "username";
}
