package controller.auth;

import model.User;
import model.db.Database;
import model.repository.UserRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public class AuthManager {
    private static final String SESSION_CURRENT_USER = "current_user";

    UserRepository userRepository;
    HttpSession session;

    public AuthManager(HttpSession session) {
        this.userRepository = new UserRepository(Database.getInstance());
        this.session = session;
    }

    public User getCurrentUser() {
        String username = (String)session.getAttribute(SESSION_CURRENT_USER);
        User user = null;

        if (username != null) {
            user = userRepository.findByUsername(username);
        }

        return user;
    }

    private void setCurrentUser(User user) {
        if (user != null) {
            session.setAttribute(SESSION_CURRENT_USER, user.getUsername());
        } else {
            session.removeAttribute(SESSION_CURRENT_USER);
        }
    }

    public boolean getIsAuthenticated() {
        return getCurrentUser() != null;
    }

    public boolean getIsAdmin() {
        return false; //FIXME
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            setCurrentUser(user);
            return true;
        }

        return false;
    }

    public void logout() {
        setCurrentUser(null);
    }
}
