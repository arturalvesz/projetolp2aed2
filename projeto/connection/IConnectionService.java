package edu.ufp.inf.lp2.projeto.connection;

import edu.ufp.inf.lp2.projeto.route.Route;

import java.util.ArrayList;
import java.util.Hashtable;

public interface IConnectionService{

    void addConnection(Connection connection, ArrayList<Connection> connections);
    void deleteConnection(Connection connection, ArrayList<Connection> connections, ArrayList<Route> routes);
    void editConnection(Connection connectionToInsert, ArrayList<Connection> connections);
    Connection searchConnection(Connection connection, ArrayList<Connection> connections);
    void printAL(ArrayList<Connection> connections);
    void loadDeletedConnectionsToFile(Connection connection);
}
