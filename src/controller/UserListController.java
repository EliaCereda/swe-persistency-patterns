package controller;

import model.Address;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet({"/users", "/users/"})
public class UserListController extends UserController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userRepository.findAll();
        Map<String, Address> addresses = addressRepository.findAll()
            .stream()
            .collect(Collectors.toMap(Address::getUsername, Function.identity()));

        req.setAttribute("users", users);
        req.setAttribute("addresses", addresses);

        getServletContext()
            .getRequestDispatcher("/WEB-INF/user-list.jsp")
            .forward(req, resp);
    }
}
