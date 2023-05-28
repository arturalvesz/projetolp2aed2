package edu.ufp.inf.lp2.projeto;

import edu.princeton.cs.algs4.*;
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

import java.io.*;
import java.util.*;

public class DataBase {

    //Database Attributes
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Route> routes = new ArrayList<Route>();
    private ArrayList<Connection> connections = new ArrayList(); //edges
    private ArrayList<Local> locals = new ArrayList<>();//nodes
    private ArrayList<Station> stations = new ArrayList<>();
    private ArrayList<Time> Schedule = new ArrayList<>();
    private Hashtable<Integer, Grafo> ht = new Hashtable<Integer, Grafo>();
    public DijkstraSP sp;


    //Interfaces
    private IUserService userService = new UserService();
    private ILocalService localService = new LocalService();
    private IRouteService routeService = new RouteService();
    private IConnectionService connectionService = new ConnectionService();
    //private ITimeService timeService = new TimeService();
    private IStationService stationService = new StationService();


    //Getters

    /**
     * Get database users
     *
     * @return
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Get database routes
     *
     * @return
     */
    public ArrayList<Route> getRoutes() {
        return routes;
    }

    /**
     * Get database locals
     *
     * @return
     */
    public ArrayList<Local> getLocals() {
        return locals;
    }

    /**
     * Get database connections
     *
     * @return
     */
    public ArrayList<Connection> getConnections() {
        return connections;
    }


    /**
     * Get Database Stations
     *
     * @return stations
     */
    public ArrayList<Station> getStations() {
        return stations;
    }
    private Network network = new Network();


    /**
     * Creath a graph and add the edges
     *
     */
    public void createGraph(String weight) {
        network.createGraph(this);
        network.addEdges(this,weight);
    }


    /**
     * Get the shorthest path from @from to @to
     *
     * @param from
     * @param to
     */
    public void shorthestPath(int from, int to) {
        sp = new DijkstraSP(network.getEwg(), from);
        if (sp.hasPathTo(to)) {
            System.out.println(sp.pathTo(to));
            System.out.println("Shortest path from vertex " + from + " to vertex " + to + " is " + sp.distTo(to));
        } else {
            System.out.println("There's no such path.");
        }
    }

    /**
     * Check graph connectivity
     *
     * @return
     */
    public Boolean checkConnectivity() {

        int flag = 0;

        Graph graph = new Graph(network.getVerticesSize(this));
        for (Connection entry : connections) {
            graph.addEdge(entry.getLocalA().getId(), entry.getLocalB().getId());
        }

        for (int i = 0; i < network.getVerticesSize(this); i++) {
            DepthFirstSearch dfs = new DepthFirstSearch(graph, i);
            for (int v = 0; v < graph.V(); v++) {
                if (dfs.marked(v)) System.out.println(v + " ");
            }
            System.out.println();
            if (dfs.count() != graph.V()) {
                flag = 1;
            }
        }
        if (flag == 1) {
            System.out.println("The graph isn't connected");
            return false;
        } else {
            System.out.println("The graph is connected");
            return true;
        }
    }


    /**
     *
     * Fill the hashtable
     */

    public void fillHash() {

        for (Connection entry : connections) {
            ArrayList<Local> vertice = new ArrayList<>();
            ArrayList<Connection> edges = new ArrayList<>();
            Grafo newGrafo = new Grafo(edges, vertice);

            for (Local local : locals) {
                if (entry.getLocalA().getId().equals(local.getId())) {
                    newGrafo.setVertices(local);
                }
                if (entry.getLocalB().getId().equals(local.getId())) {
                    newGrafo.setVertices(local);
                }
            }
            newGrafo.setEdges(entry);
            Integer key = entry.getId();
            ht.put(key, newGrafo);
        }
    }

    /**
     * Print the hashTable
     *
     */
    public void printHash() {
        System.out.println(ht.toString());
    }


    /**
     * List all the vertices
     *
     * @param types
     * @return
     */
    public ArrayList<Local> ListVertices(ArrayList<TransportType> types) {
        ArrayList<Local> localCheck = new ArrayList<Local>();
        ArrayList<Local> localAL = new ArrayList<>();

        Iterator<Map.Entry<Integer, Grafo>> iterator = ht.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Grafo> entry = iterator.next();
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

    /**
     * List all the edges
     *
     * @param types
     * @return
     */
    public ArrayList<Connection> ListEdges(ArrayList<TransportType> types) {

        ArrayList<Connection> ConnectionAL = new ArrayList<>();
        ArrayList<Connection> ConnectionCheck = new ArrayList<>();

        Iterator<Map.Entry<Integer, Grafo>> iterator = ht.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Grafo> entry = iterator.next();
            ConnectionAL = entry.getValue().getEdges();
            for (Connection connection : ConnectionAL) {
                for (Station station : stations) {
                    if (connection.getLocalA().getDesignation().equals(station.getDesignation())) {
                        for (TransportType type : types) {
                            if (station.getTransport().equals(type)) {
                                ConnectionCheck.add(connection);
                            }
                        }
                    }
                }
            }
        }
        return ConnectionCheck;
    }

    /**
     * List vertices that are less that @km away
     *
     * @param km
     * @param types
     * @param co
     * @return
     */
    public ArrayList<Local> ListVerticesKm(Double km, ArrayList<TransportType> types, Coordinate co) {

        ArrayList<Local> localCheck = new ArrayList<Local>();
        ArrayList<Local> localAL = new ArrayList<>();

        Iterator<Map.Entry<Integer, Grafo>> iterator = ht.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Grafo> entry = iterator.next();
            localAL = entry.getValue().getVertices();
            for (Local local : localAL) {
                for (Station station : stations) {
                    if (local.getDesignation().equals(station.getDesignation())) {
                        for (TransportType type : types) {
                            if (station.getTransport().equals(type)) {
                                if (calculateDistance(local.getCoordinates(), co) <= km) {
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



    private static final double EARTH_RADIUS = 6371; // Radius of the Earth in kilometers

    /**
     * Calculate the distance between coordinates
     *
     * @param c1
     * @param c2
     * @return
     */
    public double calculateDistance(Coordinate c1, Coordinate c2) {

        double lat1 = c1.getX();
        double lon1 = c1.getY();
        double lat2 = c2.getX();
        double lon2 = c2.getY();

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
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
     * @param connection - connection
     */
    public void addConnection(Connection connection) {
        connectionService.addConnection(connection, connections);
    }

    /**
     * Delete a connection
     *
     * @param connection - connection
     */
    public void deleteConnection(Connection connection) {
        connectionService.deleteConnection(connection, connections, routes);
    }

    /**
     * Replace a connection
     *
     * @param connectionToInsert - connection
     */
    public void editConnection(Connection connectionToInsert) {
        connectionService.editConnection(connectionToInsert, connections);
    }

    /**
     * Check if a connection exists
     *
     * @param connection - connection
     * @return - boolean
     */
    public Connection searchConnection(Connection connection) {
        return connectionService.searchConnection(connection, connections);
    }

    /**
     * Print all the connections
     */
    public void printConnectionAL() {
        connectionService.printAL(connections);
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

        localService.deleteLocalById(localId, locals, stations, connections);
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

    /**
     * Add routes to a given user
     *
     * @param user
     * @param beginDate
     * @param endDate
     * @return
     */
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

    /**
     * Get the users that visited certain stations in a certain time
     *
     * @param StationsName
     * @param beginDate
     * @param endDate
     * @return
     */
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

    /**
     * Get the list of stations that weren't visited in a given time
     *
     * @param beginDate
     * @param endDate
     * @return
     */
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

    /**
     * Get the top3users that visited the most stations
     *
     * @param beginDate
     * @param endDate
     * @return
     */
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

    /**
     * Get the schedule of @StationOfDep to @StationOfDest
     *
     * @param StationOfDep
     * @param StationOfDest
     * @return
     */

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

    /**
     * List the users, stations and connections lists
     */

    public void ListUsersStationsConnections() {
        System.out.println("User List: \n");
        printUserList();
        System.out.println("Stations List: \n");
        printStationsList();
        System.out.println("Connections List: \n");
        printConnectionAL();
    }

    /**
     * Load locals to database from txt
     *
     * @param path
     */

    public void loadLocalsFromFile(String path) {

        In in = new In(path);
        in.readLine();
        String designation;
        Integer id;
        Boolean IsStation = null;
        Double x, y;

        while (!in.isEmpty()) {

            String[] line = in.readLine().split(",");
            id = Integer.parseInt(line[0]);
            designation = line[1];
            if (line[2].equals("true")) {
                IsStation = true;
            } else if (line[2].equals("false")) {
                IsStation = false;
            } else {
                System.out.println("Bad input in isStation position, use true or false only");
                System.exit(-1);
            }

            x = Double.parseDouble(line[3]);
            y = Double.parseDouble(line[4]);
            Coordinate co = new Coordinate(x, y);

            Local local = new Local(id, designation, co, IsStation);
            addLocal(local);
        }
    }

    /**
     * load schedules to database from txt
     *
     * @param path
     */

    public void loadTimeFromFile(String path) {

        In in = new In(path);
        in.readLine();
        String departureStation, destinationStation;
        Integer id;

        while (!in.isEmpty()) {

            ArrayList<String> hours = new ArrayList<>();
            String[] line = in.readLine().split(",");
            id = Integer.parseInt(line[0]);
            departureStation = line[1];
            destinationStation = line[2];

            for (int i = 3; i < line.length; i++) {
                hours.add(line[i]);
            }
            Time time = new Time(id, departureStation, destinationStation, hours);
            Schedule.add(time);
        }

    }

    /**
     * Load stations to database from txt
     *
     * @param path
     */

    public void loadStationsFromFile(String path) {

        In in = new In(path);
        in.readLine();
        String designation;
        Integer id;
        TransportType transportType = null;
        Local local = null;

        while (!in.isEmpty()) {

            ArrayList<Time> schedule = new ArrayList<>();

            String[] line = in.readLine().split(",");
            id = Integer.parseInt(line[0]);
            designation = line[1];

            for (Local local1 : locals) {
                if (designation.equals(local1.getDesignation())) {
                    local = local1;
                }
            }
            TransportType[] transports = TransportType.values();
            for (TransportType transport : transports) {
                if (line[2].equals(transport.name())) {
                    transportType = transport;
                    break;
                }
            }

            for (int i = 3; i < line.length; i++) {

                for (Time time : Schedule) {
                    if (Integer.parseInt(line[i]) == time.getId()) {
                        schedule.add(time);
                    }
                }
            }

            Station station = new Station(id, designation, local, transportType, schedule);
            addStation(station);
        }


    }

    /**
     * Load connections to database from txt
     *
     * @param path
     */

    public void loadConnectionsFromFile(String path) {

        In in = new In(path);
        in.readLine();
        Integer id;
        Double distance, price, duration;
        TransportType transportType = null;
        Local localA = null;
        Local localB = null;

        while (!in.isEmpty()) {

            String[] line = in.readLine().split(",");
            id = Integer.parseInt(line[0]);
            distance = Double.parseDouble(line[3]);
            price = Double.parseDouble(line[4]);
            duration = Double.parseDouble(line[5]);

            TransportType[] transports = TransportType.values();
            for (TransportType transport : transports) {
                if (line[6].equals(transport.name())) {
                    transportType = transport;
                    break;
                }
            }

            for (Local local : locals) {
                if (line[1].equals(local.getDesignation())) { //local A
                    localA = local;
                }
                if (line[2].equals(local.getDesignation())) { //local B
                    localB = local;
                }
            }
            if (localA == null || localB == null) {
                System.out.println("Local not found, add that local to the database first");
                System.exit(-1);
            }
            Connection connection = new Connection(id, localA, localB, distance, price, duration, transportType);
            addConnection(connection);
        }
    }

    /**
     * Load routes to database from txt
     *
     * @param path
     */
    public void loadRoutesFromFile(String path) {

        In in = new In(path);
        in.readLine();
        Integer id = null;
        String departure = null, arrival = null;
        String[] startDate;
        String[] endDate;

        Date sDate = null;
        Date eDate = null;


        while (!in.isEmpty()) {

            ArrayList<Connection> connectionsList = new ArrayList<>();
            PriorityType priorityType = null;
            String[] line = in.readLine().split(",");
            id = Integer.parseInt(line[0]);
            departure = line[1];
            arrival = line[2];


            PriorityType[] priorities = PriorityType.values();
            for (PriorityType priority : priorities) {
                if (line[3].equals(priority.name())) {
                    priorityType = priority;
                    break;
                }
            }

            startDate = line[4].split("-");
            endDate = line[5].split("-");

            for (int i = 6; i < line.length; i++) {

                for (Connection connection : connections) {
                    if (Integer.parseInt(line[i]) == connection.getId()) {
                        connectionsList.add(connection);
                    }
                }
            }

            sDate = new Date(Integer.parseInt(startDate[0]), Integer.parseInt(startDate[1]), Integer.parseInt(startDate[2]));
            eDate = new Date(Integer.parseInt(endDate[0]), Integer.parseInt(endDate[1]), Integer.parseInt(endDate[2]));

            Route route = new Route(id, connectionsList, priorityType, departure, arrival, sDate, eDate);
            addRoute(route);
        }
    }

    /**
     * Load users to database from txt
     *
     * @param path
     */
    public void loadUsersFromFile(String path) {

        In in = new In(path);
        in.readLine();
        String name = null;
        Integer UserId = null;

        while (!in.isEmpty()) {
            ArrayList<Local> prefLocals = new ArrayList<>();
            ArrayList<Route> RoutesAL = new ArrayList<>();
            ArrayList<TransportType> prefTransports = new ArrayList<>();

            String[] line = in.readLine().split(",");
            UserId = Integer.parseInt(line[0]);
            name = line[1];
            int i = 0;
            int j = 0;

            TransportType[] transports = TransportType.values();
            for (i = 2; i < line.length; i++) {

                for (TransportType transport : transports) {
                    if (line[i].equals(transport.name())) {
                        prefTransports.add(transport);
                        break;
                    }
                }
                for (Local local : locals) {
                    if (line[i].equals(local.getDesignation())) {
                        prefLocals.add(local);
                        j = i + 1;
                    }
                }
            }
            User user = new User(name, UserId, prefLocals, prefTransports, RoutesAL);
            while (j < line.length) {
                for (Route route : routes) {
                    if (Integer.parseInt(line[j]) == route.getId()) {
                        RoutesAL.add(route);
                    }
                }
                j++;
            }

            addUser(user);
        }
    }
    /**
     * Load users that visited @StationsName to txt
     *
     * @param path
     * @param beginDate
     * @param endDate
     * @param StationsName
     */

    public void loadUsersVisitedToFile(String path, Date beginDate, Date endDate, String[] StationsName) {

        Out out = new Out(path);
        ArrayList<User> usersVisited = new ArrayList<>();

        out.println("Between: " + beginDate + " and: " + endDate + " this stations: ");
        for (String s : StationsName) {
            out.println(s);
        }
        out.println("Were visited by: ");
        usersVisited = UsersVisited(StationsName, beginDate, endDate);
        for (User user : usersVisited) {
            out.println("-" + user.getName());
        }
    }

    /**
     * Load not visited stations to txt
     *
     * @param path
     * @param beginDate
     * @param endDate
     */
    public void loadNotVisitedStationsToFile(String path, Date beginDate, Date endDate) {

        Out out = new Out(path);

        ArrayList<Station> notVisitedStations = new ArrayList<>();
        notVisitedStations = NotVisitedStations(beginDate, endDate);

        out.println("Between: " + beginDate + "and: " + endDate + "the following stations were not visited: ");
        for (Station station : notVisitedStations) {
            out.println("-" + station.getDesignation());
        }
    }

    /**
     * Load top3users to txt
     *
     * @param path
     * @param beginDate
     * @param endDate
     */

    public void loadTop3UsersToFile(String path, Date beginDate, Date endDate) {

        Out out = new Out(path);

        ArrayList<User> top3User = new ArrayList<>();
        top3User = Top3Users(beginDate, endDate);

        out.println("Between: " + beginDate + "and: " + endDate + "the following users used the most number of stations: ");
        for (User user : top3User) {
            out.println("-" + user.getName());
        }
    }

    /**
     * Load the top3users to bin
     *
     * @param path
     * @param beginDate
     * @param endDate
     */
    public void loadTop3UsersToBin(String path, Date beginDate, Date endDate) {

        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
            String msg1 = "Between: ";
            msg1 = msg1.concat(beginDate.toString());
            msg1 = msg1.concat(" and: ");
            msg1 = msg1.concat(endDate.toString());
            msg1 = msg1.concat(" these were the top 3 users that used the most stations");
            dos.writeChars(msg1);
            String msg2 = "\n";
            dos.writeChars(msg2);
            ArrayList<User> top3Users = new ArrayList<>();
            top3Users = Top3Users(beginDate, endDate);

            for (User user : top3Users) {
                String msg3 = "-User id: ";
                msg3 = msg3.concat(String.valueOf(user.getId()));
                dos.writeChars(msg3);
                dos.writeChars(msg2);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Load Stations that weren't visited to binary file
     *
     * @param path
     * @param beginDate
     * @param endDate
     */
    public void loadNotVisitedStationsBin(String path, Date beginDate, Date endDate) {

        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
            String msg1 = "Between: ";
            msg1 = msg1.concat(beginDate.toString());
            msg1 = msg1.concat(" and: ");
            msg1 = msg1.concat(endDate.toString());
            msg1 = msg1.concat(" these are the stations that werent visited: ");
            dos.writeChars(msg1);
            String msg2 = "\n";
            dos.writeChars(msg2);
            ArrayList<Station> NotVisitedStations = NotVisitedStations(beginDate,endDate);

            for (Station station : NotVisitedStations) {
                String msg3 = "-Station id: ";
                msg3 = msg3.concat(String.valueOf(station.getId()));
                dos.writeChars(msg3);
                dos.writeChars(msg2);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Load to binary file users that visited @StationsName
     *
     * @param path
     * @param beginDate
     * @param endDate
     * @param StationsName
     */
    public void loadUsersVisitedToBin(String path, Date beginDate, Date endDate, String[] StationsName){

        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
            String msg1 = "Between: ";
            msg1 = msg1.concat(beginDate.toString());
            msg1 = msg1.concat(" and: ");
            msg1 = msg1.concat(endDate.toString());
            msg1 = msg1.concat(" the following stations: ");
            dos.writeChars(msg1);
            String msg4 = "\n";
            dos.writeChars(msg4);

            for (String s : StationsName) {
                String msg2 = " ";
                msg2 = msg2.concat(s);
                dos.writeChars(msg2);
                dos.writeChars(msg4);
            }

            ArrayList<User> usersVisited = new ArrayList<>();
            usersVisited = UsersVisited(StationsName,beginDate, endDate);

            for (User user : usersVisited) {
                String msg3 = "-User id: ";
                msg3 = msg3.concat(String.valueOf(user.getId()));
                dos.writeChars(msg3);
                dos.writeChars(msg4);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}