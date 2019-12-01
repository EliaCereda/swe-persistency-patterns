package controller;

import model.User;
import model.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository repository = new UserRepository();
        List<User> users = repository.findUsers();

        req.setAttribute("users", users);

        getServletContext()
            .getRequestDispatcher("/WEB-INF/user-list.jsp")
            .forward(req, resp);
    }
}
