package edu.ufp.inf.lp2.projeto.route;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Graph;
import edu.ufp.inf.lp2.projeto.connection.Connection;
import edu.ufp.inf.lp2.projeto.Date;
import edu.ufp.inf.lp2.projeto.PriorityType;
import edu.ufp.inf.lp2.projeto.user.User;

import java.util.ArrayList;


public class Route {

    //Route attributes
    private Integer id;
    private ArrayList<Connection> connections;
    private PriorityType priority;
    private String departure;
    private String arrival;
    private Date startDate;
    private Date endDate;

    //Constructors
    /**
     *
     * @param id
     * @param connections
     * @param priority
     * @param departure
     * @param arrival
     * @param startDate
     * @param endDate
     */
    public Route(Integer id, ArrayList<Connection> connections, PriorityType priority, String departure, String arrival, Date startDate, Date endDate) {
        this.id = id;
        this.connections = connections;
        this.priority = priority;
        this.departure = departure;
        this.arrival = arrival;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Getters and Setters

    /**
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Connection> connections) {
        this.connections = connections;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    /**
     *Get object attributes inside a string
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", connections=" + connections +
                ", priority=" + priority +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}