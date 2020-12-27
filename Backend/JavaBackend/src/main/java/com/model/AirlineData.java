package com.model;

import java.util.List;
import java.util.Objects;

public class AirlineData {
  private List<Airline> airlines;


  public AirlineData() {
  }

  public AirlineData(List<Airline> airlines) {
    this.airlines = airlines;
  }

  public List<Airline> getAirlines() {
    return this.airlines;
  }

  public void setAirlines(List<Airline> airlines) {
    this.airlines = airlines;
  }

  public AirlineData airlines(List<Airline> airlines) {
    this.airlines = airlines;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AirlineData)) {
            return false;
        }
        AirlineData airlineData = (AirlineData) o;
        return Objects.equals(airlines, airlineData.airlines);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(airlines);
  }

  @Override
  public String toString() {
    return "{" +
      " airlines='" + getAirlines() + "'" +
      "}";
  }
  
}
