package edu.ufp.inf.lp2.projeto.connection;

import edu.ufp.inf.lp2.projeto.route.Route;

import java.util.ArrayList;
import java.util.Hashtable;

public interface IConnectionService{

    void addConnection(Integer Key, Connection connection, Hashtable<Integer, Connection> connectionsHT);
    void deleteConnection(Connection connection, Hashtable<Integer, Connection> connectionsHT, ArrayList<Route> routes);
    void editConnection(Integer KeyToReplace, Connection connectionToInsert, Hashtable<Integer, Connection> connectionsHT);
    Connection searchConnection(Connection connection, Hashtable<Integer, Connection> connectionsHT);
    void printHT(Hashtable<Integer, Connection> connectionsHT);
}
