package edu.ufp.inf.lp2.projeto.station;

import edu.princeton.cs.algs4.Out;
import edu.ufp.inf.lp2.projeto.Time;
import edu.ufp.inf.lp2.projeto.connection.Connection;
import edu.ufp.inf.lp2.projeto.local.Local;
import edu.ufp.inf.lp2.projeto.user.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class StationService implements IStationService {


    public void addStation(Station station, ArrayList<Station> stations) {
        stations.add(station);
    }

    /**
     * Deletes given station
     *
     * @param stationId -station ID
     */
    public void deleteStation(int stationId, ArrayList<Station> stations, ArrayList<Local> locals) {

        for (Station station : stations) {
            if (station.getId().equals(stationId)) {
                //Remove station from locals
                locals.remove(station.getLocal());
                //Remove station

                loadDeletedStationsToFile(station);
                stations.remove(station);
            }
        }
    }

    /**
     * Finds the stationID and replaces it
     *
     * @param station - station
     */
    public void editStation(Station station, ArrayList<Station> stations) {

        Iterator<Station> s = stations.iterator();
        int i = 0;
        while (s.hasNext()) {
            if (s.next().getId().equals(station.getId())) {
                stations.set(i, station);
            }
            i++;
        }
    }

    /**
     * Check if a station exists
     *
     * @param station - station
     * @return
     */
    public Station searchStation(Station station, ArrayList<Station> stations) {

        for (Station element : stations) {
            if (element.getId().equals(station.getId())) {

                return element;
            }
        }
        return null;
    }

    /**
     * Print all the stations
     */
    public void printStationsList(ArrayList<Station> stations) {
        for (Station station : stations) {
            System.out.println(station.toString());
        }
    }

    /**
     * load the deleted station to a file
     *
     * @param station - station to load
     */
    public void loadDeletedStationsToFile(Station station){
        String path = "deletedStations.txt";
        Out out = new Out(path);

        out.println("Id: " + station.getId() + ",Designation " + station.getDesignation() + ",Type: " + station.getTransport());

        out.print("Schedule: ");
        for(Time time : station.getSchedule()){
            out.print("," + time.getId());
        }
    }
}
