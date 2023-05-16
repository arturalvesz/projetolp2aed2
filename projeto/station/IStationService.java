package edu.ufp.inf.lp2.projeto.station;

import edu.ufp.inf.lp2.projeto.local.Local;

import java.util.ArrayList;

public interface IStationService {

    void addStation(Station station, ArrayList<Station> stations);

    void deleteStation(int stationID, ArrayList<Station> stations, ArrayList<Local> locals);

    void editStation(Station station, ArrayList<Station> stations);

    Station searchStation(Station station, ArrayList<Station> stations);

    void printStationsList(ArrayList<Station> stations);


}
