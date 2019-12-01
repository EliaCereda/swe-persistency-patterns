package controller;

import controller.auth.AuthManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        AuthManager auth = (AuthManager)req.getAttribute("auth");

        if (auth.login(username, password)) {
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("username", username);
            req.setAttribute("message", "Login failed! Invalid username or password.");

            doGet(req, resp);
        }
    }
}
