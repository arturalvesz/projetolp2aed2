package edu.ufp.inf.lp2.projeto.route;

import edu.ufp.inf.lp2.projeto.user.User;

import java.util.ArrayList;

public interface IRouteService {

    void addRoute(Route route, ArrayList<Route> routes);

    void deleteRouteById(int routeId, ArrayList<Route> routes, ArrayList<User> users);

    void editRoute(Route route, ArrayList<Route> routes);

    Route searchRoute(Route route, ArrayList<Route> routes);

    void printRoutesList(ArrayList<Route> routes);

    void loadDeletedRoutesToFile(Route route);

}
