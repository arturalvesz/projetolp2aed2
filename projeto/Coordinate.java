package edu.ufp.inf.lp2.projeto;


public class Coordinate{

  public Double x;

  public Double y;

  public Coordinate(Double x, Double y) {
    this.x = x;
    this.y = y;
  }

  public Double getX() {
    return x;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public Double getY() {
    return y;
  }

  public void setY(Double y) {
    this.y = y;
  }



  @Override
  public String toString() {
    return "Coordinate{" +
            "x=" + x +
            ", y=" + y +
            '}';
  }
}
