package edu.ufp.inf.lp2.projeto.user;


import edu.ufp.inf.lp2.projeto.local.Local;
import edu.ufp.inf.lp2.projeto.TransportType;
import edu.ufp.inf.lp2.projeto.route.Route;

import java.util.ArrayList;


public class User {

  private String name;
  private Integer id;
  private ArrayList<Local> prefLocals;
  private ArrayList<TransportType> prefTransports;
  private ArrayList<Route> routesAL;

  public User(String name, Integer id, ArrayList<Local> prefLocals, ArrayList<TransportType> prefTransports, ArrayList<Route> routesAL) {
    this.name = name;
    this.id = id;
    this.prefLocals = prefLocals;
    this.prefTransports = prefTransports;
    this.routesAL = routesAL;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ArrayList<Local> getPrefLocals() {
    return prefLocals;
  }

  public void setPrefLocals(ArrayList<Local> prefLocals) {
    this.prefLocals = prefLocals;
  }

  public ArrayList<TransportType> getPrefTransports() {
    return prefTransports;
  }

  public void setPrefTransports(ArrayList<TransportType> prefTransports) {
    this.prefTransports = prefTransports;
  }

  public ArrayList<Route> getRoutes() {
    return routesAL;
  }

  public void setRoutes(ArrayList<Route> routesAL) {
    this.routesAL = routesAL;
  }

  @Override
  public String toString() {
    return "User{" +
            "name='" + name + '\'' +
            ", id=" + id +
            ", prefLocals=" + prefLocals +
            ", prefTransports=" + prefTransports +
            ", routes=" + routesAL +
            '}';
  }
}