package edu.ufp.inf.lp2.projeto.user;

import edu.ufp.inf.lp2.projeto.route.Route;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    @Override
    public void addUser(List<User> users, User user, List<Route> routes) {

        users.add(user);

        if (user.getRoutes() != null && !user.getRoutes().isEmpty()) {
            routes.addAll(user.getRoutes());
        }
    }

    @Override
    public void deleteUserById(List<User> users, int userId, List<Route> routes) {
        //Find user
        for (User user1 : users) {
            if (user1.getId().equals(userId)) {
                //Validate if user has any routes
                if (user1.getRoutes() != null && !user1.getRoutes().isEmpty()) {

                    //Remove all user routes
                    routes.removeAll(user1.getRoutes());
                }

                //Remove user
                users.remove(user1);
            }
        }
    }

    @Override
    public User searchUser(List<User> users, User user) {

        for (User element : users) {
            if (element.getId().equals(user.getId())
                    || element.getName().equals(user.getName())) {

                return element;
            }
        }

        return null;
    }

    @Override
    public void editUser(List<User> users, User user) {

        for (User element : users) {
            if (element.getId().equals(user.getId())) {
                if (user.getName() != null) {
                    element.setName(user.getName());
                }
                if (user.getPrefLocals() != null) {
                    element.setPrefLocals(user.getPrefLocals());
                }
                if (user.getRoutes() != null) {
                    element.setRoutes(user.getRoutes());
                }
                if (user.getPrefTransports() != null) {
                    element.setPrefTransports(user.getPrefTransports());
                }
            }
        }

    }

    @Override
    public void printAllUsers(List<User> users) {
        users.forEach(user -> {
            System.out.println(user.toString());
        });
    }
}
