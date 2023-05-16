package edu.ufp.inf.lp2.projeto.route;

import edu.ufp.inf.lp2.projeto.user.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class RouteService implements IRouteService{

    /**
     * Inserts route in the ArrayList
     *
     * @param route - route
     */
    public void addRoute(Route route, ArrayList<Route> routes) {
        routes.add(route);
    }

    /**
     * Deletes given route
     *
     * @param routeId -route ID
     */
    public void deleteRouteById(int routeId, ArrayList<Route> routes, ArrayList<User> users) {

        for(Route route : routes) {
            if (route.getId().equals(routeId)) {
                for (User user : users) {
                    if (route.equals(user.getRoutes())) {
                        user.getRoutes().remove(route);
                    }
                }
                routes.remove(route);
            }
        }
    }

    /**
     * Finds the routeID and replaces it
     *
     * @param route - route
     */
    public void editRoute(Route route, ArrayList<Route> routes) {

        Iterator<Route> r = routes.iterator();
        int i = 0;
        while (r.hasNext()) {
            if (r.next().getId().equals(route.getId())) {
                routes.set(i, route);
            }
            i++;
        }
    }

    /**
     * Check if a route exists
     *
     * @param route - route
     * @return
     */
    public Route searchRoute(Route route, ArrayList<Route> routes) {

        for (Route element : routes) {
            if (element.getId().equals(route.getId())) {

                return element;
            }
        }

        return null;
    }

    /**
     * Print all the Routes
     */
    public void printRoutesList(ArrayList<Route> routes) {
        for (Route route : routes) {
            System.out.println(route.toString());
        }
    }
}
