package edu.ufp.inf.lp2.projeto;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.ufp.inf.lp2.projeto.connection.ConnectionService;
import edu.ufp.inf.lp2.projeto.connection.IConnectionService;
import edu.ufp.inf.lp2.projeto.local.ILocalService;
import edu.ufp.inf.lp2.projeto.local.Local;
import edu.ufp.inf.lp2.projeto.connection.Connection;
import edu.ufp.inf.lp2.projeto.local.LocalService;
import edu.ufp.inf.lp2.projeto.route.IRouteService;
import edu.ufp.inf.lp2.projeto.route.Route;
import edu.ufp.inf.lp2.projeto.route.RouteService;
import edu.ufp.inf.lp2.projeto.station.IStationService;
import edu.ufp.inf.lp2.projeto.station.Station;
import edu.ufp.inf.lp2.projeto.station.StationService;
import edu.ufp.inf.lp2.projeto.user.IUserService;
import edu.ufp.inf.lp2.projeto.user.User;
import edu.ufp.inf.lp2.projeto.user.UserService;

import java.lang.reflect.Array;
import java.util.*;

public class DataBase {

    //Database Attributes
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Route> routes = new ArrayList<Route>();
    private Hashtable<Integer, Connection> connectionsHT = new Hashtable<>(); //edges
    private ArrayList<Local> locals = new ArrayList<>();//nodes
    private ArrayList<Station> stations = new ArrayList<>();
    private Hashtable<String, Grafo> ht = new Hashtable<String, Grafo>();
    public EdgeWeightedDigraph ewg;

    public DijkstraSP sp;

    //Interfaces
    private IUserService userService = new UserService();
    private ILocalService localService = new LocalService();
    private IRouteService routeService = new RouteService();
    private IConnectionService connectionService = new ConnectionService();
    //private ITimeService timeService = new TimeService();
    private IStationService stationService = new StationService();


    //Getters
    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public Hashtable<Integer, Connection> getConnectionsHT() {
        return connectionsHT;
    }


    /**
     * Get Database Stations
     *
     * @return stations
     */
    public ArrayList<Station> getStations() {
        return stations;
    }

    //Database methods


    //GRAPH
    public Integer getEdgesSize() {
        return connectionsHT.size();
    }

    public Integer getVerticesSize() {
        return locals.size();
    }

    public void createGraph() {
        ewg = new EdgeWeightedDigraph(getVerticesSize() + 1);
    }

    public void printGraph() {
        System.out.println(ewg.toString());
    }

    public void addEdges() {
        ewg = new EdgeWeightedDigraph(getVerticesSize() + 1);
        Iterator<Map.Entry<Integer, Connection>> iterator = connectionsHT.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Connection> entry = iterator.next();
            DirectedEdge edge = new DirectedEdge(entry.getValue().getLocalA().getId(), entry.getValue().getLocalB().getId(), entry.getValue().getDistance());
            ewg.addEdge(edge);
        }
    }


    public void shorthestPath(int from, int to) {
        sp = new DijkstraSP(ewg, from);
    }

    public void fillHash() {
        Iterator<Map.Entry<Integer, Connection>> iterator = connectionsHT.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Connection> entry = iterator.next();
            ArrayList<Local> vertice = new ArrayList<>();
            ArrayList<Connection> edges = new ArrayList<>();
            Grafo newGrafo = new Grafo(edges, vertice);

            for (Local local : locals) {
                if (entry.getValue().getLocalA().getId().equals(local.id)) {
                    newGrafo.setVertices(local);
                }
                if (entry.getValue().getLocalB().getId().equals(local.id)) {
                    newGrafo.setVertices(local);
                }
            }
            newGrafo.setEdges(entry.getValue());
            String key = entry.getValue().getId().toString();
            ht.put(key, newGrafo);
        }
    }

    public void printHash() {
        System.out.println(ht.toString());
    }

    public ArrayList<Local> ListVertices(ArrayList<TransportType> types) {
        ArrayList<Local> localCheck = new ArrayList<Local>();
        ArrayList<Local> localAL = new ArrayList<>();

        Iterator<Map.Entry<String, Grafo>> iterator = ht.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Grafo> entry = iterator.next();
            localAL = entry.getValue().getVertices();
            for (Local local : localAL) {
                for (Station station : stations) {
                    if (local.getDesignation().equals(station.getDesignation())) {
                        for (TransportType type : types) {
                            if (station.getTransport().equals(type)) {
                                localCheck.add(local);
                            }
                        }
                    }
                }
            }
        }
        return localCheck;
    }

    public ArrayList<Local> ListVerticesKm(Double km, ArrayList<TransportType> types, Coordinate co) {

        ArrayList<Local> localCheck = new ArrayList<Local>();
        ArrayList<Local> localAL = new ArrayList<>();

        Iterator<Map.Entry<String, Grafo>> iterator = ht.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Grafo> entry = iterator.next();
            localAL = entry.getValue().getVertices();
            for (Local local : localAL) {
                for (Station station : stations) {
                    if (local.getDesignation().equals(station.getDesignation())) {
                        for (TransportType type : types) {
                            if(station.getTransport().equals(type)){
                                if(CalcDistance(local.getCoordinates(), co) <= km){
                                    localCheck.add(local);
                                }
                            }
                        }
                    }
                }
            }
        }
        return localCheck;
    }


    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public Double CalcDistance(Coordinate c1, Coordinate c2) {
        double theta = c1.getY() - c2.getY();
        double dist = Math.sin(deg2rad(c1.getX())) * Math.sin(deg2rad(c2.getX()));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return (dist);
    }

    //USER

    /**
     * Inserts user into Arraylist
     *
     * @param user - user object
     */
    public void addUser(User user) {

        userService.addUser(users, user, routes);
    }

    /**
     * Deletes given user
     *
     * @param userID - user ID
     */
    public void deleteUser(int userID) {

        userService.deleteUserById(users, userID, routes);
    }

    /**
     * Finds user name and replaces with the new object
     *
     * @param user - user object
     */
    public void editUser(User user) {

        userService.editUser(users, user);
    }

    /**
     * Searches for user
     *
     * @param user - user ID
     * @return true or false depending wether there's an user or not
     */
    public User searchUser(User user) {

        return userService.searchUser(users, user);
    }

    /**
     * Prints arrayList of users
     */
    public void printUserList() {

        userService.printAllUsers(users);
    }

    /**
     * Inserts route in the ArrayList
     *
     * @param route - route
     */
    public void addRoute(Route route) {
        routeService.addRoute(route, routes);
    }

    /**
     * Deletes given route
     *
     * @param routeID -route ID
     */
    public void deleteRoute(int routeID) {

        routeService.deleteRouteById(routeID, routes, users);
    }

    /**
     * Finds the routeID and replaces it
     *
     * @param route - route
     */
    public void editRoute(Route route) {
        routeService.editRoute(route, routes);
    }

    /**
     * Check if a route exists
     *
     * @param route - route
     * @return
     */
    public Route searchRoute(Route route) {
        return routeService.searchRoute(route, routes);
    }

    /**
     * Print all the Routes
     */
    public void printRoutesList() {
        routeService.printRoutesList(routes);
    }

    /**
     * Insert a new connection
     *
     * @param Key        - key
     * @param connection - connection
     */
    public void addConnection(Integer Key, Connection connection) {
        connectionService.addConnection(Key, connection, connectionsHT);
    }

    /**
     * Delete a connection
     *
     * @param connection - connection
     */
    public void deleteConnection(Connection connection) {
        connectionService.deleteConnection(connection, connectionsHT, routes);
    }

    /**
     * Replace a connection
     *
     * @param KeyToReplace       - key
     * @param connectionToInsert - connection
     */
    public void editConnection(Integer KeyToReplace, Connection connectionToInsert) {
        connectionService.editConnection(KeyToReplace, connectionToInsert, connectionsHT);
    }

    /**
     * Check if a connection exists
     *
     * @param connection - connection
     * @return - boolean
     */
    public Connection searchConnection(Connection connection) {
        return connectionService.searchConnection(connection, connectionsHT);
    }

    /**
     * Print all the connections
     */
    public void printConnectionHT() {
        connectionService.printHT(connectionsHT);
    }

    /**
     * Insert a new local
     *
     * @param local - local
     */
    public void addLocal(Local local) {
        localService.addLocal(local, locals);
    }

    /**
     * Delete a local
     *
     * @param localId - local
     */
    public void deleteLocalById(Integer localId) {

        localService.deleteLocalById(localId, locals, stations, connectionsHT);
    }

    /**
     * Replace a local
     *
     * @param localToInsert - local
     */
    public void editLocal(Local localToInsert) {
        localService.editLocal(locals, localToInsert);
    }

    /**
     * Check if a local exists
     *
     * @param local - local
     * @return - boolean
     */
    public Local searchLocal(Local local) {
        return localService.searchLocal(locals, local);
    }

    /**
     * Print all the locals
     */
    public void printLocals() {
        localService.printLocals(locals);
    }

    /**
     * Inserts stations in the ArrayList
     *
     * @param station - station
     */
    public void addStation(Station station) {
        stationService.addStation(station, stations);
    }

    /**
     * Deletes given station
     *
     * @param stationID -station ID
     */
    public void deleteStation(int stationID) {
        stationService.deleteStation(stationID, stations, locals);
    }

    /**
     * Finds the stationID and replaces it
     *
     * @param station - station
     */
    public void editStation(Station station) {
        stationService.editStation(station, stations);
    }

    /**
     * Check if a station exists
     *
     * @param station - station
     * @return
     */
    public Station searchStation(Station station) {

        return stationService.searchStation(station, stations);
    }

    /**
     * Print all the stations
     */
    public void printStationsList() {
        stationService.printStationsList(stations);
    }

    public void addRouteToUser(String UserName, Route route) {

        for (User user1 : users) {
            if (user1.getName().equals(UserName)) {
                user1.getRoutes().add(route);
            }
        }
    }

    public ArrayList<Route> UserRoutes(User user, Date beginDate, Date endDate) {

        ArrayList<Route> UserR = new ArrayList<>();
        for (User user1 : users) {
            if (user1.getId().equals(user.getId())) {
                for (Route route1 : user.getRoutes()) {
                    if ((route1.getStartDate().afterDate(beginDate) || route1.getStartDate().isEqual(beginDate)) && (route1.getEndDate().beforeDate(endDate) || route1.getEndDate().isEqual(endDate))) {
                        UserR.add(route1);
                    }
                }
            }
        }
        return UserR;
    }

    public ArrayList<User> UsersVisited(String[] StationsName, Date beginDate, Date endDate) {
        ArrayList<User> UserV = new ArrayList<>();
        int count = 0;
        for (User user1 : users) {
            for (Route route1 : user1.getRoutes()) {
                if ((route1.getStartDate().afterDate(beginDate) || route1.getStartDate().isEqual(beginDate)) && (route1.getEndDate().beforeDate(endDate) || route1.getEndDate().isEqual(endDate))) {
                    for (Connection connection1 : route1.getConnections()) {
                        if (connection1.getLocalA().isStation() || connection1.getLocalB().isStation()) {
                            for (String s : StationsName) {
                                if (connection1.getLocalA().getDesignation().equals(s) || connection1.getLocalB().getDesignation().equals(s)) {
                                    count++;
                                }
                            }
                        }
                        if (count == StationsName.length) {
                            UserV.add(user1);
                        }
                    }
                }
            }
        }
        return UserV;
    }

    public ArrayList<Station> NotVisitedStations(Date beginDate, Date endDate) {
        ArrayList<Station> notVistdStns = new ArrayList<>(stations);
        ArrayList<User> UserV = new ArrayList<>();

        for (User user1 : users) {
            for (Route route1 : user1.getRoutes()) {
                if ((route1.getStartDate().afterDate(beginDate) || route1.getStartDate().isEqual(beginDate)) && (route1.getEndDate().beforeDate(endDate) || route1.getEndDate().isEqual(endDate))) {
                    for (Connection connection1 : route1.getConnections()) {
                        if (connection1.getLocalA().isStation()) {
                            for (int i = 0; i < notVistdStns.size(); i++) {
                                if (connection1.getLocalA().getDesignation().equals(notVistdStns.get(i).getDesignation())) {
                                    notVistdStns.remove(i);
                                }
                            }
                        }
                        if (connection1.getLocalB().isStation()) {
                            for (int i = 0; i < notVistdStns.size(); i++) {
                                if (connection1.getLocalB().getDesignation().equals(notVistdStns.get(i).getDesignation())) {
                                    notVistdStns.remove(i);
                                }
                            }
                        }
                    }
                }
            }
        }
        return notVistdStns;
    }

    public ArrayList<User> Top3Users(Date beginDate, Date endDate) {
        ArrayList<User> Top3UsersRoutes = new ArrayList<>();

        Map<String, Integer> tempMap = new HashMap<>();

        for (User user1 : users) {
            int counter = 0;
            for (Route route1 : user1.getRoutes()) {
                if ((route1.getStartDate().afterDate(beginDate) || route1.getStartDate().isEqual(beginDate)) && (route1.getEndDate().beforeDate(endDate) || route1.getEndDate().isEqual(endDate))) {
                    for (Connection connection1 : route1.getConnections()) {
                        if (connection1.getLocalA().isStation()) {
                            counter++;
                        }
                        if (connection1.getLocalB().isStation()) {
                            counter++;
                        }
                    }
                }
            }
            tempMap.put(user1.getName(), counter);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(tempMap.entrySet());

        Comparator<Map.Entry<String, Integer>> valueComparator = (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue());
        Collections.sort(list, valueComparator);
        Map<String, Integer> sortedMap = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        int i = 0;
        for (Map.Entry<String, Integer> map : sortedMap.entrySet()) {
            for (User user : users) {
                if (map.getKey().equals(user.getName())) {
                    Top3UsersRoutes.add(user);
                }
            }
            i++;
            if (i == 2) {
                break;
            }
        }
        return Top3UsersRoutes;
    }

    public Time StationsSchedule(String StationOfDep, String StationOfDest) {


        for (Station station : stations) {
            if (station.getDesignation().equals(StationOfDep)) {
                for (Time time1 : station.getSchedule()) {
                    if (time1.getDestinationStation().equals(StationOfDest)) {
                        System.out.println(station.getDesignation() + " Schedule to " + StationOfDest);
                        return time1;
                    }
                }
            }
        }
        return null;
    }

    public void ListUsersStationsConnections() {
        System.out.println("User List: \n");
        printUserList();
        System.out.println("Stations List: \n");
        printStationsList();
        System.out.println("Connections List: \n");
        printConnectionHT();
    }
}