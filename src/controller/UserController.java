package controller;

import model.db.Database;
import model.repository.UserRepository;

import javax.servlet.http.HttpServlet;

class UserController extends HttpServlet {
    protected UserRepository repository;

    public UserController() {
        repository = new UserRepository(Database.getInstance());
    }
}
