package edu.ufp.inf.lp2.projeto.local;

import edu.ufp.inf.lp2.projeto.connection.Connection;
import edu.ufp.inf.lp2.projeto.station.Station;

import java.util.ArrayList;
import java.util.Hashtable;

public interface ILocalService {

    void addLocal(Local local, ArrayList<Local> locals);
    void deleteLocalById(Integer localId, ArrayList<Local> locals, ArrayList<Station> stations, Hashtable<Integer, Connection> connectionsHT);
    void editLocal(ArrayList<Local> locals, Local local);
    Local searchLocal(ArrayList<Local> locals, Local local);
    void printLocals(ArrayList<Local> locals);

}
