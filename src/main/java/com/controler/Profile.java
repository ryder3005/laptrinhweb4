    package com.controler;

    import com.entity.User;
    import com.service.UserService;
    import com.service.implement.UserServiceImpl;
    import io.github.cdimascio.dotenv.Dotenv;
    import jakarta.persistence.EntityManager;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.MultipartConfig;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.servlet.http.Part;

    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.UUID;
    @MultipartConfig(
            fileSizeThreshold = 1024 * 1024,  // 1 MB
            maxFileSize = 1024 * 1024 * 10,       // 10 MB
            maxRequestSize = 1024 * 1024 * 15     // 15 MB
    )
    @WebServlet("/profile")
    public class Profile extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if (req.getSession().getAttribute("account") == null){
                resp.sendRedirect("/login");
            }else{
                User user = (User) req.getSession().getAttribute("account");

            }

        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            EntityManager em = (EntityManager) req.getAttribute("em");
            UserService userService = new UserServiceImpl(em);
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String fullname = req.getParameter("fullname");
            String phone = req.getParameter("phone");
            User user = (User) req.getSession().getAttribute("account");
            String image=null;
            for (Part part : req.getParts()) {
                if (part.getName().equals("images") && part.getSize() > 0) {
                    image = saveImage(part, "user");

                }
            }
            user.setFullName(fullname);
            user.setPhone(phone);
            user.setImage(image);
            userService.update(user);
        }
        public static String saveImage(Part filePart, String subFolder) throws IOException {

            if (filePart == null || filePart.getSize() == 0) {
                return null;
            }

            // Định nghĩa các loại MIME Type được phép và phần mở rộng tương ứng
            final Map<String, String> allowedImageTypes = new HashMap<>();
            allowedImageTypes.put("image/jpeg", ".jpg");
            allowedImageTypes.put("image/png", ".png");
            allowedImageTypes.put("image/gif", ".gif");
            allowedImageTypes.put("image/webp", ".webp");
            allowedImageTypes.put("image/bmp", ".bmp");

            String contentType = filePart.getContentType();
            String ext = allowedImageTypes.get(contentType);

            if (ext == null) {
                return null;
            }

            // Tạo thư mục lưu nếu chưa tồn tại (sử dụng Files.createDirectories)
            String uploadBaseDir= Dotenv.load().get("UPLOAD_FILE_PATH");
            Path uploadPath = Paths.get(uploadBaseDir, subFolder);
            Files.createDirectories(uploadPath); // Sẽ tạo thư mục nếu chưa có và không ném ngoại lệ nếu đã tồn tại

            // Tên file mới bằng UUID
            String newFileName = UUID.randomUUID().toString().substring(0, 8) + ext;

            // Lưu file lên disk
            Path filePath = uploadPath.resolve(newFileName);
            filePart.write(filePath.toString());

            return subFolder+"/"+newFileName;
        }
    }
