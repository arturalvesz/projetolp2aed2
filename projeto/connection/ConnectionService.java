package edu.ufp.inf.lp2.projeto.connection;

import edu.ufp.inf.lp2.projeto.route.Route;

import java.util.ArrayList;
import java.util.Hashtable;

public class ConnectionService implements IConnectionService{

    /**
     * Insert a new connection
     *
     * @param Key        - key
     * @param connection - connection
     */
    public void addConnection(Integer Key, Connection connection, Hashtable<Integer, Connection> connectionsHT) {
        connectionsHT.put(Key, connection);
    }

    /**
     * Delete a connection
     *
     * @param connection - connection
     */
    public void deleteConnection(Connection connection, Hashtable<Integer, Connection> connectionsHT, ArrayList<Route> routes) {

        for(Route route1 : routes){
            if(route1.getConnections().contains(connection)) {
                route1.getConnections().remove(connection);
            }
        }
        connectionsHT.values().remove(connection);
    }

    /**
     * Replace a connection
     *
     * @param KeyToReplace       - key
     * @param connectionToInsert - connection
     */
    public void editConnection(Integer KeyToReplace, Connection connectionToInsert, Hashtable<Integer, Connection> connectionsHT) {
        connectionsHT.replace(KeyToReplace, connectionToInsert);
    }

    /**
     * Check if a connection exists
     *
     * @param connection - connection
     * @return - boolean
     */
    public Connection searchConnection(Connection connection, Hashtable<Integer, Connection> connectionsHT) {

        if(connectionsHT.contains(connection)){
            return connection;
        }
        return null;
    }

    /**
     * Print all the connections
     */
    public void printHT(Hashtable<Integer, Connection> connectionsHT) {
        System.out.println(connectionsHT.toString());
    }


}
