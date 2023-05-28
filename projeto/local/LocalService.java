package edu.ufp.inf.lp2.projeto.local;


import edu.princeton.cs.algs4.Out;
import edu.ufp.inf.lp2.projeto.connection.Connection;
import edu.ufp.inf.lp2.projeto.station.Station;

import java.util.*;

public class LocalService implements ILocalService{


    /**
     * Insert a new local
     *
     * @param local - local
     */
    public void addLocal(Local local, ArrayList<Local> locals) {
        locals.add(local);
    }

    /**
     * Delete a local
     *
     * @param locals - list of locals
     * @param localId - local ID
     */
    public void deleteLocalById(Integer localId, ArrayList<Local> locals, ArrayList<Station> stations, ArrayList<Connection> connections) {

        //Cycle to go through the locals array list
        for(Local local : locals){
            if(local.isStation()){
                for(Station station : stations){
                    if(local.getDesignation().equals(station.getDesignation())){
                        stations.remove(station);
                    }
                }
            }

            for(Connection entry : connections){
                if(entry.getLocalA().getId().equals(local.getId()) || entry.getLocalB().getId().equals(local.getId())){
                    connections.remove(entry);
                }
            }

            loadDeletedLocalsToFile(local);
            //Remove local
            locals.remove(local);
        }
    }

    /**
     * Replace a local
     *
     * @param locals - list of locals
     * @param local - localToEdit
     */
    public void editLocal(ArrayList<Local> locals, Local local) {

        for (Local element : locals) {
            if (element.getId().equals(local.getId())) {
                if (local.getDesignation() != null) {
                    element.setDesignation(local.getDesignation());
                }
                if (local.getCoordinates() != null) {
                    element.setCoordinates(local.getCoordinates());
                }
            }
        }
    }

    /**
     * Search a given local
     *
     * @param locals
     * @param local
     * @return
     */
    public Local searchLocal(ArrayList<Local> locals, Local local) {

        for (Local element : locals) {
            if (element.getId().equals(local.getId())
                    || element.getDesignation().equals(local.getDesignation())) {

                return element;
            }
        }
        return null;
    }

    /**
     * Print all the locals
     */
    public void printLocals(ArrayList<Local> locals) {
        System.out.println(locals.toString());
    }


    public void loadDeletedLocalsToFile(Local local){
        String path = "deletedLocals.txt";
        Out out = new Out(path);

        out.println("Id: " + local.getId() + ",Designation: " + local.getDesignation()
                + ",isStation: " + local.isStation()  + ",x: " + local.getCoordinates().getX()
                + ",y: " + local.getCoordinates().getY());
    }
}
