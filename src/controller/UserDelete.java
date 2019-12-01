package controller;

import model.db.Database;
import model.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-user")
public class UserDelete extends HttpServlet {
    private UserRepository repository;

    public UserDelete() {
        repository = new UserRepository(Database.getInstance());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        repository.deleteByUsername(username);

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
