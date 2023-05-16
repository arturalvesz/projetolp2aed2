package edu.ufp.inf.lp2.projeto.station;

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
}
