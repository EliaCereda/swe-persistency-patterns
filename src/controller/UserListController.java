package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/users", "/users/"})
public class UserListController extends UserController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = repository.findAll();

        req.setAttribute("users", users);

        getServletContext()
            .getRequestDispatcher("/WEB-INF/user-list.jsp")
            .forward(req, resp);
    }
}
