package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/signup")
public class UserSignupController extends UserController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> bestFriends = repository.findAll();

        req.setAttribute("bestFriends", bestFriends);

        getServletContext()
            .getRequestDispatcher("/WEB-INF/user-edit.jsp")
            .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String bestFriend = req.getParameter("best_friend");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setBestFriend(bestFriend);

        repository.insert(user);

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
