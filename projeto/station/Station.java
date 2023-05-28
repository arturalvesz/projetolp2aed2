package edu.ufp.inf.lp2.projeto.station;

import edu.ufp.inf.lp2.projeto.local.Local;
import edu.ufp.inf.lp2.projeto.TransportType;
import edu.ufp.inf.lp2.projeto.Time;

import java.util.ArrayList;

public class Station{

    private Integer id;

    private String designation;

    private Local local;

    private TransportType transport;

    private ArrayList<Time> schedule;

    public Station(Integer id, String designation, Local local, TransportType transport, ArrayList<Time> schedule) {
        this.id = id;
        this.designation = designation;
        this.local = local;
        this.transport = transport;
        this.schedule = schedule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public TransportType getTransport() {
        return transport;
    }

    public void setTransport(TransportType transport) {
        this.transport = transport;
    }

    public ArrayList<Time> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Time> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", local=" + local +
                ", transport=" + transport +
                ", schedule=" + schedule +
                '}';
    }
}