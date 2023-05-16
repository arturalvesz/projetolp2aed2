package edu.ufp.inf.lp2.projeto.connection;


import edu.ufp.inf.lp2.projeto.local.Local;
import edu.ufp.inf.lp2.projeto.TransportType;

public class Connection {

    public Integer id;
    public Local localA;

    public Local localB;

    public Double distance;

    public Double price;

    public Double duration;

    public TransportType transport;


    public Connection(Integer id, Local localA, Local localB, Double distance, Double price, Double duration, TransportType transport) {
        this.id = id;
        this.localA = localA;
        this.localB = localB;
        this.distance = distance;
        this.price = price;
        this.duration = duration;
        this.transport = transport;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Local getLocalA() {
        return localA;
    }

    public void setLocalA(Local localA) {
        this.localA = localA;
    }

    public Local getLocalB() {
        return localB;
    }

    public void setLocalB(Local localB) {
        this.localB = localB;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public TransportType getTransport() {
        return transport;
    }

    public void setTransport(TransportType transport) {
        this.transport = transport;
    }


    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", localA=" + localA +
                ", localB=" + localB +
                ", distance=" + distance +
                ", price=" + price +
                ", duration=" + duration +
                ", transport=" + transport +
                '}' + "\n";
    }
}

