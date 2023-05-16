package edu.ufp.inf.lp2.projeto.user;

import edu.ufp.inf.lp2.projeto.route.Route;

import java.util.List;

public interface IUserService {
    void addUser(List<User> users, User user, List<Route> routes);

    void deleteUserById(List<User> users, int userId, List<Route> routes);

    User searchUser(List<User> users, User user);

    void editUser(List<User> users, User user);

    void printAllUsers(List<User> users);
}
