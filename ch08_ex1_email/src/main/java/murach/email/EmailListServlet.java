package murach.email;

import murach.business.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class EmailListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp")
                           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName  = req.getParameter("lastName");
        String email     = req.getParameter("email");

        User user = new User(firstName, lastName, email);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        @SuppressWarnings("unchecked")
        ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");
        if (users == null) {
            users = new ArrayList<>();
            session.setAttribute("users", users);
        }
        users.add(user);

        req.setAttribute("currentDate", new Date());

        getServletContext().getRequestDispatcher("/thanks.jsp")
                           .forward(req, resp);
    }
}
