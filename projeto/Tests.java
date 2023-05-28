package edu.ufp.inf.lp2.projeto;

import edu.ufp.inf.lp2.projeto.connection.Connection;
import edu.ufp.inf.lp2.projeto.local.Local;
import edu.ufp.inf.lp2.projeto.route.Route;
import edu.ufp.inf.lp2.projeto.station.Station;
import edu.ufp.inf.lp2.projeto.user.User;

import java.util.ArrayList;

public abstract class Tests {

    static DataBase dataBase = new DataBase();
    static ArrayList<Local> n1 = new ArrayList<Local>();
    static ArrayList<TransportType> n2 = new ArrayList<TransportType>();


    static ArrayList<Connection> c1 = new ArrayList<>();
    static ArrayList<Connection> c3 = new ArrayList<>();
    static ArrayList<Connection> c6 = new ArrayList<>();



    static Date d1 = new Date(1,1,2023);
    Date d2 = new Date(2,1,2023);
    static Date d3 = new Date(1,2,2023);
    Date d4 = new Date(2,2,2023);
    static Date d5 = new Date(18,5,2023);
    static Date d6 = new Date(19,5,2023);
    Date d7 = new Date(3,3,2023);
    Date d8 = new Date(4,3,2023);

    static Coordinate co = new Coordinate(5.0,4.0);
    static Local local0 = new Local(0,"Aeroporto Paris", co, true);
    static Local local1 = new Local(1,"Hotel Paris", co, false);
    static Local local2 = new Local(2,"Aeroporto Madrid", co, true);
    static Local local3 = new Local(3,"Hotel Madrid", co, false);
    static Local local4 = new Local(4,"Aeroporto Porto", co, true);
    static Local local5 = new Local(5,"Aeroporto Lisboa", co, true);

    static Connection c2 = new Connection(0,local0, local1, 10.0,12.0,15.0,TransportType.TVDE);
    static Connection c4 = new Connection(1,local1, local2, 10.0,12.0,15.0,TransportType.TVDE);
    static Connection c5 = new Connection(2,local2, local1, 10.0,12.0,15.0,TransportType.TVDE);
    static Connection c7 = new Connection(3,local2, local3, 1.0,12.0,15.0,TransportType.TRAIN);
    static Connection c8 = new Connection(4,local3, local4, 15.0,12.0,15.0,TransportType.TVDE);
    static Connection c9 = new Connection(5,local4, local5, 2.0,12.0,15.0,TransportType.TVDE);

    static ArrayList<Time> time1 = new ArrayList<>();

    String h1 = "9:30";
    String h2 = "14:30";
    String h3 = "19:30";

    static Station s1 = new Station(1,"Aeroporto Porto", local4,TransportType.PLANE,time1);
    static Station s2 = new Station(2,"Aeroporto Lisboa", local5,TransportType.PLANE,time1);
    static Station s3 = new Station(3,"Aeroporto Madrid", local2,TransportType.PLANE,time1);
    Station s4 = new Station(4,"Aeroporto Paris", local0,TransportType.PLANE,time1);

    static ArrayList<Route> zeRoutes = new ArrayList<Route>();
    static ArrayList<Route> r4 = new ArrayList<Route>();
    static ArrayList<Route> r6 = new ArrayList<Route>();




    //r2.add(r1);
    //r2.add(r5);

    //r4.add(r3);



    static User ze = new User("ze", 1, n1,n2, zeRoutes);
    static User za = new User("za", 2, n1,n2, r4);
    static User ri = new User("ri", 4, n1,n2, r6);

    static Route r1 = new Route(1,c1,PriorityType.TIME, "Aeroporto Porto", "Aeroporto Lisboa",d1,d3);
    static Route r2 = new Route(2,c3,PriorityType.TIME, "Aeroporto Madrid", "Aeroporto Paris",d1,d3);
    static Route r3 = new Route(3,c6,PriorityType.TIME, "Aeroporto Lisboa", "Aeroporto Madrid",d1,d3);


    //USERS
    public static void addMultipleUsers(){
        //Criar 3 users
        //Adicionar os users à lista
        //User esta função na main sem instanciar objeto
        dataBase.addUser(ze);
        dataBase.addUser(za);
        dataBase.addUser(ri);
    }
    public static void removeUser(){
        dataBase.deleteUser(2);
    }

    //ROUTES
    public static void addMultipleRoutes(){
        dataBase.addRoute(r1);
        dataBase.addRoute(r2);
        dataBase.addRoute(r3);
    }

    public static void deleteRoute(){
        dataBase.deleteRoute(2);
    }

    public static void addUserRoutes(){
        dataBase.addRouteToUser("ze", r1);
    }

    public static void UserRoutes(){
        System.out.println(dataBase.UserRoutes(ze,d1,d3).toString());
    }





    //CONNECTIONS
    public static void addMultipleConnections(){
        //Criar 3 users
        //Adicionar os users à lista
        //User esta função na main sem instanciar objeto
        dataBase.addConnection(c2);
        dataBase.addConnection(c4);
        dataBase.addConnection(c5);
        dataBase.addConnection(c7);
        dataBase.addConnection(c8);
        dataBase.addConnection(c9);
    }

    //LOCALS
    public static void addMultipleLocals(){
        //Criar 3 users
        //Adicionar os users à lista
        //User esta função na main sem instanciar objeto
        dataBase.addLocal(local1);
        dataBase.addLocal(local2);
        dataBase.addLocal(local3);
        dataBase.addLocal(local4);
        dataBase.addLocal(local5);
        dataBase.addLocal(local0);
    }
    public static void removeLocal(){
        dataBase.deleteLocalById(1);

    }

    //STATIONS
    public static void addMultipleStations(){
        dataBase.addStation(s1);
        dataBase.addStation(s2);
        dataBase.addStation(s3);
    }

    public static void createGraph(){
        dataBase.createGraph("distance");
    }

    public static void shortestPath(){
        dataBase.shorthestPath(local2.getId(),local5.getId());
        //dataBase.routesList(1);
        dataBase.checkConnectivity();
    }
    public static void bestKPaths(int k){
        for(int i = 0; i < k; i++){
            dataBase.shorthestPath(local2.getId(), local5.getId());
        }
    }

    public static void PrintLists(){
        //dataBase.ListUsersStationsConnections();
    }

    public static void hash(){
        dataBase.fillHash();
        //dataBase.printHash();
    }

    public static void files(){
        /*dataBase.loadLocalsFromFile("C:/Users/artur/Desktop/Java_Fx/src/main/java/edu/ufp/inf/lp2/projeto/locals.txt");
        dataBase.loadConnectionsFromFile("src/main/java/edu/ufp/inf/lp2/projeto/connections.txt");
        dataBase.loadRoutesFromFile("src/main/java/edu/ufp/inf/lp2/projeto/routes.txt");
        dataBase.loadUsersFromFile("src/main/java/edu/ufp/inf/lp2/projeto/users.txt");
        dataBase.loadStationsFromFile("src/main/java/edu/ufp/inf/lp2/projeto/station.txt");
        dataBase.loadTimeFromFile("src/main/java/edu/ufp/inf/lp2/projeto/time.txt");

        dataBase.loadNotVisitedStationsBin("src/main/java/edu/ufp/inf/lp2/projeto/notVisitedStations.bin",d5,d6);

        String[] stations = new String[2];
        stations[0] = "Aeroporto Porto";
        stations[1] = "Aeroporto Lisboa";
        dataBase.loadUsersVisitedToFile("src/main/java/edu/ufp/inf/lp2/projeto/usersVisited.txt", d5,d6, stations);

         */
    }
}

