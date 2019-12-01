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
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet({"/users", "/users/"})
public class UserListController extends UserController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String field = req.getParameter("field");
        String query = req.getParameter("query");

        List<User> users = userRepository.findAll();

        Map<String, User> allUsers = users
            .stream()
            .collect(Collectors.toMap(User::getUsername, Function.identity()));

        Map<String, Address> addresses = addressRepository.findAll()
            .stream()
            .collect(Collectors.toMap(Address::getUsername, Function.identity()));

        if (field != null && query != null && !query.equals("")) {
            switch (field) {
                case "name":
                    users = userRepository.findAllByName(query);
                    break;

                case "address":
                    users = userRepository.findAllByStreetAddress(query);
                    break;

                case "best_friend":
                    users = userRepository.findAllByBestFriend(query);
                    break;

                default:
                    throw new UnsupportedOperationException();
            }
        }

        req.setAttribute("users", users);
        req.setAttribute("addresses", addresses);
        req.setAttribute("allUsers", allUsers);
        req.setAttribute("field", field);
        req.setAttribute("query", query);

        getServletContext()
            .getRequestDispatcher("/WEB-INF/user-list.jsp")
            .forward(req, resp);
    }
}
