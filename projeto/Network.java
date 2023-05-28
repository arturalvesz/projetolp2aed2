package edu.ufp.inf.lp2.projeto;

import edu.princeton.cs.algs4.*;
import edu.ufp.inf.lp2.projeto.DataBase;
import edu.ufp.inf.lp2.projeto.TransportType;
import edu.ufp.inf.lp2.projeto.connection.Connection;
import edu.ufp.inf.lp2.projeto.local.Local;
import edu.ufp.inf.lp2.projeto.station.Station;
import edu.ufp.inf.lp2.projeto.user.User;
import java.util.ArrayList;
public class Network{

    private EdgeWeightedDigraph ewg;

    public Network() {
    }
    public Network(EdgeWeightedDigraph ewg) {
        this.ewg = ewg;
    }

    public EdgeWeightedDigraph getEwg() {
        return ewg;
    }

    public void setEwg(EdgeWeightedDigraph ewg) {
        this.ewg = ewg;
    }

    public Integer getEdgesSize(DataBase dataBase) {
        return dataBase.getConnections().size();
    }

    public Integer getVerticesSize(DataBase dataBase) {
        return dataBase.getLocals().size();
    }

    public void createGraph(DataBase dataBase) {
        ewg = new EdgeWeightedDigraph(getVerticesSize(dataBase));
    }

    public void printGraph() {
        System.out.println(ewg.toString());
    }

    /**
     * Add edges
     *
     * @param dataBase
     * @param weight
     */

    public void addEdges(DataBase dataBase, String weight) {
        ewg = new EdgeWeightedDigraph(getVerticesSize(dataBase));
        for(Connection connection : dataBase.getConnections()){
            if(weight.equals("distance")) {
                DirectedEdge edge = new DirectedEdge(connection.getLocalA().getId(), connection.getLocalB().getId(), connection.getDistance());
                ewg.addEdge(edge);
            }else if(weight.equals("duration")) {
                    DirectedEdge edge = new DirectedEdge(connection.getLocalA().getId(), connection.getLocalB().getId(), connection.getDuration());
                    ewg.addEdge(edge);
            }else if(weight.equals("price")) {
                DirectedEdge edge = new DirectedEdge(connection.getLocalA().getId(), connection.getLocalB().getId(), connection.getPrice());
                ewg.addEdge(edge);
            }
        }
    }

    /**
     * Add edges by given types
     *
     * @param dataBase
     * @param weight
     * @param typeAL
     */

    public void addEdgesByTypes(DataBase dataBase, String weight, ArrayList<TransportType> typeAL) {
        for(Connection connection : dataBase.getConnections()) {
            for (TransportType type : typeAL) {
                if(connection.getLocalA().isStation() && connection.getLocalB().isStation()){
                    for(Station station : dataBase.getStations()){
                        if(connection.getLocalA().getDesignation().equals(station.getDesignation())
                            || connection.getLocalB().getDesignation().equals(station.getDesignation())){
                            if(station.getTransport().equals(type)){
                               this.addEdges(dataBase, weight);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Add edges by the users preferences
     *
     * @param dataBase
     * @param weight
     * @param user
     */

    public void addEdgesByUserPrefs(DataBase dataBase, String weight, User user){

        for(Connection connection : dataBase.getConnections()){
            for(Local local : user.getPrefLocals()){
                if(local.getId().equals(connection.getLocalA().getId()) || local.getId().equals(connection.getLocalB().getId())){
                    addEdges(dataBase,weight);
                }
            }
        }
        this.addEdgesByTypes(dataBase, weight, user.getPrefTransports());
    }



}
