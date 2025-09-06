package murach.download;

import murach.business.User;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DownloadServlet extends HttpServlet {

    @SuppressWarnings("unchecked")
    private void ensureCatalog(ServletContext sc) {
        List<DownloadItem> items = (List<DownloadItem>) sc.getAttribute("downloads");
        if (items == null) {
            items = new java.util.ArrayList<>();
            items.add(new DownloadItem("adamn", "ADAMN", "ADAMN.mp3"));
            items.add(new DownloadItem("caooc20", "Cao Ốc 20", "Cao Ốc 20.mp3"));
            items.add(new DownloadItem("muathangsau", "Mưa Tháng Sáu", "Mưa Tháng Sáu.mp3"));
            items.add(new DownloadItem("noitinhyeuketthuc", "Nơi Tình Yêu Kết Thúc", "Nơi Tình Yêu Kết Thúc.mp3"));
            items.add(new DownloadItem("tranghoamaymua", "Trắng Hoa Mây Mưa", "Trắng Hoa Mây Mưa.mp3"));
            sc.setAttribute("downloads", items);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ServletContext sc = getServletContext();
        ensureCatalog(sc);

        String action = req.getParameter("action");
        if (action == null) action = "index";

        switch (action) {
            case "register":
                sc.getRequestDispatcher("/register.jsp").forward(req, resp);
                break;

            case "viewCookies":
                sc.getRequestDispatcher("/view_cookies.jsp").forward(req, resp);
                break;

            case "deleteCookies":
                deleteCookies(req, resp);
                break;

            case "download":
                sc.getRequestDispatcher("/download.jsp").forward(req, resp);
                break;

            default: // index
                sc.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("saveRegistration".equals(action)) {
            saveRegistration(req, resp);
        } else {
            doGet(req, resp);
        }
    }

    private void saveRegistration(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String firstName = req.getParameter("firstName");
        String lastName  = req.getParameter("lastName");
        String email     = req.getParameter("email");

        // Lưu user vào session
        User user = new User(firstName, lastName, email);
        req.getSession().setAttribute("user", user);

        // Tạo cookie (2 năm)
        int twoYears = 60 * 60 * 24 * 365 * 2;
        String ctx = req.getContextPath();

        Cookie fn = new Cookie("firstName", firstName == null ? "" : firstName);
        Cookie em = new Cookie("email", email == null ? "" : email);
        fn.setMaxAge(twoYears); em.setMaxAge(twoYears);
        fn.setPath(ctx); em.setPath(ctx);
        resp.addCookie(fn); resp.addCookie(em);

        // Quay lại đúng trang (PRG)
        String backCode = req.getParameter("code"); // nếu đăng ký khi vào trang download
        if (backCode != null && !backCode.isBlank()) {
            resp.sendRedirect(ctx + "/download?action=download&code=" + backCode);
        } else {
            resp.sendRedirect(ctx + "/download?action=index");
        }
    }

    private void deleteCookies(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("firstName".equals(c.getName()) || "email".equals(c.getName())) {
                    c.setMaxAge(0);
                    c.setPath(req.getContextPath());
                    resp.addCookie(c);
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/download?action=index");
    }
}
