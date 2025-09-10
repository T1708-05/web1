package murach.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import murach.model.User;
import murach.data.UserDB;

@WebServlet({"/", "/join", "/thanks"})
public class EmailListServlet extends HttpServlet {

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String path = req.getServletPath();
    if ("/thanks".equals(path)) {
      forward(req, resp, "/thanks.jsp");
    } else {
      forward(req, resp, "/index.jsp");
    }
  }

  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = req.getParameter("email");
    String first = req.getParameter("firstName");
    String last = req.getParameter("lastName");

    User u = new User(email, first, last);
    UserDB.insert(u);
    req.setAttribute("user", u);

    forward(req, resp, "/thanks.jsp");
  }

  private void forward(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
    RequestDispatcher rd = req.getRequestDispatcher(path);
    rd.forward(req, resp);
  }
}
