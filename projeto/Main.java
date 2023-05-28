package edu.ufp.inf.lp2.projeto;

import edu.ufp.inf.lp2.projeto.local.Local;
import edu.ufp.inf.lp2.projeto.connection.Connection;
import edu.ufp.inf.lp2.projeto.route.Route;
import edu.ufp.inf.lp2.projeto.station.Station;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        DataBase dataBase = new DataBase();

        ArrayList<String> n1 = new ArrayList<String>();
        n1.add("Porto");
        ArrayList<String> n2 = new ArrayList<String>();
        n2.add("Carro");

        ArrayList<Connection> c1 = new ArrayList<>();
        ArrayList<Connection> c3 = new ArrayList<>();
        ArrayList<Connection> c6 = new ArrayList<>();



        Date d1 = new Date(1,1,2023);
        Date d2 = new Date(2,1,2023);
        Date d3 = new Date(1,2,2023);
        Date d4 = new Date(2,2,2023);
        Date d5 = new Date(1,3,2023);
        Date d6 = new Date(2,3,2023);
        Date d7 = new Date(3,3,2023);
        Date d8 = new Date(4,3,2023);

        Coordinate co = new Coordinate(41.18475,-8.60484);
        Coordinate co2 = new Coordinate(41.17299,-8.61093);

        Local local1 = new Local(0,"Aeroporto Paris", co, true);
        Local local2 = new Local(1,"Hotel Paris", co, false);
        Local local3 = new Local(2,"Aeroporto Madrid", co, true);
        Local local4 = new Local(3,"Hotel Madrid", co, false);
        Local local5 = new Local(4,"Aeroporto Porto", co, true);
        Local local6 = new Local(5,"Aeroporto Lisboa", co, true);

        Connection c2 = new Connection(1,local1, local2, 10.0,12.0,15.0,TransportType.TVDE);
        Connection c4 = new Connection(2,local2, local3, 10.0,12.0,15.0,TransportType.TVDE);
        Connection c5 = new Connection(3,local3, local4, 10.0,12.0,15.0,TransportType.TVDE);
        Connection c7 = new Connection(4,local4, local5, 10.0,12.0,15.0,TransportType.TVDE);
        Connection c8 = new Connection(5,local5, local6, 10.0,12.0,15.0,TransportType.TVDE);


        c1.add(c2);
        c1.add(c4);
        c3.add(c5);
        c3.add(c7);
        c6.add(c2);

        ArrayList<Time> time1 = new ArrayList<>();

        String h1 = "9:30";
        String h2 = "14:30";
        String h3 = "19:30";

        ArrayList<String> sch = new ArrayList<>();
        sch.add(h1);
        sch.add(h2);
        sch.add(h3);

        Station s1 = new Station(1,"Aeroporto Porto", local5,TransportType.PLANE,time1);
        Station s2 = new Station(2,"Aeroporto Lisboa", local6,TransportType.PLANE,time1);
        Station s3 = new Station(3,"Aeroporto Madrid", local3,TransportType.PLANE,time1);
        Station s4 = new Station(4,"Aeroporto Paris", local1,TransportType.PLANE,time1);


        //Route r3 = new Route(2,c3, PriorityType.PRICE, "Lisboa", "Madrid", d1,d2);
        ArrayList<Route> r2 = new ArrayList<Route>();
        ArrayList<Route> r4 = new ArrayList<Route>();
        ArrayList<Route> r6 = new ArrayList<Route>();


        System.out.println(dataBase.calculateDistance(co, co2));

        //Tests.addMultipleUsers();
        //Tests.addMultipleLocals();
        //Tests.addMultipleConnections();
        //Tests.addMultipleStations();
        //Tests.removeLocal();
        //Tests.removeUser();
        //Tests.addMultipleRoutes();
        //Tests.deleteRoute();
        //Tests.addUserRoutes();
        //Tests.UserRoutes();
        //Tests.createGraph();
        //Tests.shortestPath();
        //Tests.files();
        //Tests.hash();
        //Tests.PrintLists();
        //r2.add(r1);
        //r2.add(r5);
        //System.out.println("dist: " + dataBase.CalcDistance(co,co2));
        //r4.add(r3);


        //System.out.println(dataBase.StationsSchedule("Aeroporto Porto", "Aeroporto Paris").toString());

        //System.out.println(dataBase.NotVisitedStations(d1,d4));
        //dataBase.addRouteToUser("za", r5);
        //dataBase.addUser(jo);
        //dataBase.addUser(ri);
        //dataBase.printUserList();

        //System.out.println(dataBase.UserRoutes(ze,d1,d6).toString());
        //String[] stations1 = {"Aeroporto Lisboa", "Aeroporto Porto"};
        //System.out.println(dataBase.UsersVisited(stations1, d1,d4).toString());
        //System.out.println(dataBase.Top3Users(d1,d8));
        //System.out.println(dataBase.searchUser(ze));


        //dataBase.addRoute(r1);
        //dataBase.addRoute(r3);
        //dataBase.printRoutesList();
        /*dataBase.addConnection(1,c2);
        System.out.println("print c2");
        dataBase.printHT();
        dataBase.addConnection(2,c4);
        System.out.println("add c4");
        dataBase.printHT();
        */


        //System.out.println(ze.toString());


    }
}
