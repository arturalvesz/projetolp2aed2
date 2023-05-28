package edu.ufp.inf.lp2.projeto.connection;

import edu.princeton.cs.algs4.Out;
import edu.ufp.inf.lp2.projeto.TransportType;
import edu.ufp.inf.lp2.projeto.local.Local;
import edu.ufp.inf.lp2.projeto.route.Route;
import edu.ufp.inf.lp2.projeto.user.User;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class ConnectionService implements IConnectionService{

    /**
     * Insert a new connection
     *
     * @param connection - connection
     */
    public void addConnection(Connection connection, ArrayList<Connection> connections) {
        connections.add(connection);
    }

    /**
     * Delete a connection
     *
     * @param connection - connection
     */
    public void deleteConnection(Connection connection, ArrayList<Connection> connections, ArrayList<Route> routes) {

        for(Route route1 : routes){
            if(route1.getConnections().contains(connection)) {
                route1.getConnections().remove(connection);
            }
        }

        loadDeletedConnectionsToFile(connection);

        connections.remove(connection);
    }

    /**
     * Replace a connection
     *
     * @param connectionToInsert - connection
     */
    public void editConnection(Connection connectionToInsert, ArrayList<Connection> connections) {

        Iterator<Connection> c = connections.iterator();
        int i = 0;
        while (c.hasNext()) {
            if (c.next().getId().equals(connectionToInsert.getId())) {
                connections.set(i, connectionToInsert);
            }
            i++;
        }
    }

    /**
     * Check if a connection exists
     *
     * @param connection - connection
     * @return - boolean
     */
    public Connection searchConnection(Connection connection, ArrayList<Connection> connections) {

        if(connections.contains(connection)){
            return connection;
        }
        return null;
    }

    /**
     * Print all the connections
     */
    public void printAL(ArrayList<Connection> connections) {

        for (Connection connection : connections) {
            System.out.println(connection.toString());
        }
    }

    /**
     * load the deleted connection to a file
     *
     * @param connection - connection to load
     */
    public void loadDeletedConnectionsToFile(Connection connection){
        String path = "deletedConnections.txt";
        Out out = new Out(path);

        out.println("Id: " + connection.getId() + ",Local A: " + connection.getLocalA().getDesignation()
                + ",Local B: " + connection.getLocalB().getDesignation() + ",Type: " + connection.getTransport()
                + ",Distance: " + connection.getDistance() + ",Price" + connection.getPrice() + ",Duration" + connection.getDuration());
    }
}
