package edu.ufp.inf.lp2.projeto.local;

import edu.ufp.inf.lp2.projeto.Coordinate;

public class Local {

  public Integer id;

  public String designation;

  public Coordinate coordinates;

  public boolean isStation;

  public Local(Integer id, String designation, Coordinate coordinates, boolean isStation) {
    this.id = id;
    this.designation = designation;
    this.coordinates = coordinates;
    this.isStation = isStation;
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

  public Coordinate getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinate coordinates) {
    this.coordinates = coordinates;
  }

  public boolean isStation() {
    return isStation;
  }

  public void setStation(boolean station) {
    isStation = station;
  }

  @Override
  public String toString() {
    return "Local{" +
            "id=" + id +
            ", designation='" + designation + '\'' +
            ", coordinates=" + coordinates +
            ", isStation=" + isStation +
            '}';
  }
}