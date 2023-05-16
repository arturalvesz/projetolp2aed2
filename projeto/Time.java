package edu.ufp.inf.lp2.projeto;

import edu.ufp.inf.lp2.projeto.station.Station;

import java.util.ArrayList;

public class Time {

  private Integer id;

  private Station departureStation;

  private Station destinationStation;

  private ArrayList<String> hour;

  public Time(Integer id, Station departureStation, Station destinationStation, ArrayList<String> hour) {
    this.id = id;
    this.departureStation = departureStation;
    this.destinationStation = destinationStation;
    this.hour = hour;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Station getDepartureStation() {
    return departureStation;
  }

  public void setDepartureStation(Station departureStation) {
    this.departureStation = departureStation;
  }

  public Station getDestinationStation() {
    return destinationStation;
  }

  public void setDestinationStation(Station destinationStation) {
    this.destinationStation = destinationStation;
  }

  public ArrayList<String> getHour() {
    return hour;
  }

  public void setHour(ArrayList<String> hour) {
    this.hour = hour;
  }

  @Override
  public String toString() {
    return "Time{" +
            "id=" + id +
            ", departureStation=" + departureStation +
            ", destinationStation=" + destinationStation +
            ", hour=" + hour +
            '}';
  }
}