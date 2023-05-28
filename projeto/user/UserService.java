package edu.ufp.inf.lp2.projeto.user;

import edu.princeton.cs.algs4.Out;
import edu.ufp.inf.lp2.projeto.DataBase;
import edu.ufp.inf.lp2.projeto.TransportType;
import edu.ufp.inf.lp2.projeto.local.Local;
import edu.ufp.inf.lp2.projeto.route.Route;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    /**
     * add Users to a list
     *
     * @param users
     * @param user
     * @param routes
     */
    @Override
    public void addUser(List<User> users, User user, List<Route> routes) {

        users.add(user);

        if (user.getRoutes() != null && !user.getRoutes().isEmpty()) {
            routes.addAll(user.getRoutes());
        }
    }

    /**
     * Delete a user by his id
     *
     * @param users
     * @param userId
     * @param routes
     */
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

                //Load user
                loadDeletedUsersToFile(user1);
                //Remove user
                users.remove(user1);
            }
        }
    }

    /**
     * Search a user
     *
     * @param users
     * @param user
     * @return
     */
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

    /**
     * Edit a user
     *
     * @param users
     * @param user
     */
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

    /**
     * Print all users from a given list
     *
     * @param users
     */
    @Override
    public void printAllUsers(List<User> users) {
        users.forEach(user -> {
            System.out.println(user.toString());
        });
    }


    public void loadDeletedUsersToFile(User user){
        String path = "deletedUsers.txt";
        Out out = new Out(path);

        out.println("Id: " + user.getId() + ",Name: " + user.getName() + ",Type: ");

        for(TransportType type : user.getPrefTransports()){
            out.print(type + ",");
        }
        out.print("Local: ");
        for(Local local : user.getPrefLocals()){
            out.print(local.getDesignation() + ",");
        }

        out.print("Route: ");
        for(Route route : user.getRoutes()){
            out.print(route.getId() + ",");
        }
    }
}
