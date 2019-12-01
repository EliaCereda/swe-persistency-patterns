package controller;

import model.User;
import model.db.Database;
import model.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class UserSignup extends HttpServlet {
    private UserRepository repository;

    public UserSignup() {
        repository = new UserRepository(Database.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
            .getRequestDispatcher("/WEB-INF/user-edit.jsp")
            .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);

        repository.insert(user);

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
