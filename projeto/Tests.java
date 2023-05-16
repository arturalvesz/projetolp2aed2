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
    Date d5 = new Date(1,3,2023);
    Date d6 = new Date(2,3,2023);
    Date d7 = new Date(3,3,2023);
    Date d8 = new Date(4,3,2023);

    static Coordinate co = new Coordinate(5.0,4.0);
    static Local local1 = new Local(1,"Aeroporto Paris", co, true);
    static Local local2 = new Local(2,"Hotel Paris", co, false);
    static Local local3 = new Local(3,"Aeroporto Madrid", co, true);
    static Local local4 = new Local(4,"Hotel Madrid", co, false);
    static Local local5 = new Local(5,"Aeroporto Porto", co, true);
    static Local local6 = new Local(6,"Aeroporto Lisboa", co, true);

    static Connection c2 = new Connection(1,local1, local2, 10.0,12.0,15.0,TransportType.TVDE);
    static Connection c4 = new Connection(2,local2, local3, 10.0,12.0,15.0,TransportType.TVDE);
    static Connection c5 = new Connection(3,local3, local4, 10.0,12.0,15.0,TransportType.TVDE);
    static Connection c7 = new Connection(4,local4, local5, 10.0,12.0,15.0,TransportType.TVDE);

    static ArrayList<Time> time1 = new ArrayList<>();

    String h1 = "9:30";
    String h2 = "14:30";
    String h3 = "19:30";

    static Station s1 = new Station(1,"Aeroporto Porto", local5,TransportType.PLANE,time1);
    static Station s2 = new Station(2,"Aeroporto Lisboa", local6,TransportType.PLANE,time1);
    static Station s3 = new Station(3,"Aeroporto Madrid", local3,TransportType.PLANE,time1);
    Station s4 = new Station(4,"Aeroporto Paris", local1,TransportType.PLANE,time1);

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
        dataBase.addConnection(1,c2);
        dataBase.addConnection(2,c4);
        dataBase.addConnection(3,c5);
        dataBase.addConnection(4,c7);
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
        dataBase.createGraph();
        dataBase.addEdges();
        //dataBase.printGraph();
    }
    public static void PrintLists(){
        dataBase.ListUsersStationsConnections();
    }

    public static void hash(){
        dataBase.fillHash();
        dataBase.printHash();
    }
}

