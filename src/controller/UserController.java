package controller;

import model.db.Database;
import model.repository.AddressRepository;
import model.repository.UserRepository;

import javax.servlet.http.HttpServlet;

class UserController extends HttpServlet {
    protected UserRepository userRepository;
    protected AddressRepository addressRepository;

    public UserController() {
        Database db = Database.getInstance();

        userRepository = new UserRepository(db);
        addressRepository = new AddressRepository(db);
    }
}
