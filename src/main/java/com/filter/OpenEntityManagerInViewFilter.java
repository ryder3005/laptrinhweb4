package com.filter;

import com.config.JpaConfig;
import jakarta.persistence.EntityManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class OpenEntityManagerInViewFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        EntityManager em = JpaConfig.getEntityManager();
        try {
            // Gắn EntityManager vào request attribute để DAO lấy dùng
            request.setAttribute("em", em);
            em.getTransaction().begin();

            chain.doFilter(request, response);

            if (em.getTransaction().isActive() && !em.getTransaction().getRollbackOnly()) {
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close(); // 👉 Đóng EntityManager cuối request
        }
    }
}
