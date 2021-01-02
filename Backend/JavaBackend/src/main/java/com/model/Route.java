package com.model;

import java.util.Objects;

public class Route {
  private String airlineStart;
  private String airlineEnd;
  private String supplier;

  public Route() {
  }

  public Route(String airlineStart, String airlineEnd, String supplier) {
    this.airlineStart = airlineStart;
    this.airlineEnd = airlineEnd;
    this.supplier = supplier;
  }

  public String getAirlineStart() {
    return this.airlineStart;
  }

  public void setAirlineStart(String airlineStart) {
    this.airlineStart = airlineStart;
  }

  public String getAirlineEnd() {
    return this.airlineEnd;
  }

  public void setAirlineEnd(String airlineEnd) {
    this.airlineEnd = airlineEnd;
  }

  public String getSupplier() {
    return this.supplier;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  public Route airlineStart(String airlineStart) {
    this.airlineStart = airlineStart;
    return this;
  }

  public Route airlineEnd(String airlineEnd) {
    this.airlineEnd = airlineEnd;
    return this;
  }

  public Route supplier(String supplier) {
    this.supplier = supplier;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Route)) {
            return false;
        }
        Route route = (Route) o;
        return Objects.equals(airlineStart, route.airlineStart) && Objects.equals(airlineEnd, route.airlineEnd) && Objects.equals(supplier, route.supplier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(airlineStart, airlineEnd, supplier);
  }

  @Override
  public String toString() {
    return "{" +
      " airlineStart='" + getAirlineStart() + "'" +
      ", airlineEnd='" + getAirlineEnd() + "'" +
      ", supplier='" + getSupplier() + "'" +
      "}";
  }
}
