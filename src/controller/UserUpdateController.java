package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/users/*")
public class UserUpdateController extends UserController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String[] pathComponents = url.split("/");
        String lastComponent = pathComponents[pathComponents.length - 1];

        User user = repository.findByUsername(lastComponent);
        List<User> bestFriends = repository.findAll()
                .parallelStream()
                // Prevent users from being their own best friends.
                .filter((User bestFriend) -> !bestFriend.getUsername().equals(user.getUsername()))
                .collect(Collectors.toList());

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        req.setAttribute("user", user);
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

        User user = repository.findByUsername(username);

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        user.setPassword(password);
        user.setName(name);
        user.setBestFriend(bestFriend);

        repository.update(user);

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
