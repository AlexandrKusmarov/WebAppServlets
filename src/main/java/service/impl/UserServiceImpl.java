package service.impl;

import dao.impl.UserDAOimpl;
import model.Courses;
import model.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAOimpl userDAOimpl = new UserDAOimpl();

    @Override
    public List<Courses> getAllCourses() {
        return userDAOimpl.listAllCourses();
    }

    @Override
    public boolean createUser(User user) {
        return userDAOimpl.insertNewUser(user);
    }

    @Override
    public boolean findUserByLoginAndPswd(String login, String password) {
        return userDAOimpl.findUserByLoginAndPswd(login, password);
    }

    @Override
    public boolean findUserByLogin(String login) {
        return userDAOimpl.findUserByLogin(login);
    }
}
