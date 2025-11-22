package com.controler;

import com.entity.User;
import com.service.UserService;
import com.service.implement.UserServiceImpl;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1 MB
        maxFileSize = 1024 * 1024 * 10,   // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
@WebServlet("/profile")
public class Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Chuyển sang trang profile.jsp hiển thị thông tin user
        User user = (User) session.getAttribute("account");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        EntityManager em = (EntityManager) req.getAttribute("em");
        UserService userService = new UserServiceImpl(em);

        User user = (User) session.getAttribute("account");

        // Lấy dữ liệu từ form
        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");

        // Xử lý ảnh upload
        String imagePath = null;
        for (Part part : req.getParts()) {
            if ("image".equals(part.getName()) && part.getSize() > 0) {
                imagePath = saveImage(part, "user");
            }
        }

        // Cập nhật thông tin user
        if (fullName != null) user.setFullName(fullName);
        if (phone != null) user.setPhone(phone);
        if (imagePath != null) user.setImage(imagePath);

        userService.update(user);

        // Cập nhật session
        session.setAttribute("account", user);

        resp.sendRedirect(req.getContextPath() + "/profile");
    }

    private static String saveImage(Part filePart, String subFolder) throws IOException {
        if (filePart == null || filePart.getSize() == 0) return null;

        final Map<String, String> allowedTypes = new HashMap<>();
        allowedTypes.put("image/jpeg", ".jpg");
        allowedTypes.put("image/png", ".png");
        allowedTypes.put("image/gif", ".gif");
        allowedTypes.put("image/webp", ".webp");
        allowedTypes.put("image/bmp", ".bmp");

        String ext = allowedTypes.get(filePart.getContentType());
        if (ext == null) return null;

        String baseDir = Dotenv.load().get("UPLOAD_FILE_PATH");
        Path uploadPath = Paths.get(baseDir, subFolder);
        Files.createDirectories(uploadPath);

        String fileName = UUID.randomUUID().toString().substring(0, 8) + ext;
        Path filePath = uploadPath.resolve(fileName);
        filePart.write(filePath.toString());

        return subFolder + "/" + fileName;
    }
}
