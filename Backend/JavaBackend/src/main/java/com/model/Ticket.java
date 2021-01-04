package com.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket extends MongoIdModel {
  private String flightId;
  private String seatType;
  private Integer count;


  public Ticket() {
  }

  public Ticket(String flightId, String seatType, Integer count) {
    this.flightId = flightId;
    this.seatType = seatType;
    this.count = count;
  }

  public String getFlightId() {
    return this.flightId;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  public String getSeatType() {
    return this.seatType;
  }

  public void setSeatType(String seatType) {
    this.seatType = seatType;
  }

  public Integer getCount() {
    return this.count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Ticket flightId(String flightId) {
    this.flightId = flightId;
    return this;
  }

  public Ticket seatType(String seatType) {
    this.seatType = seatType;
    return this;
  }

  public Ticket count(Integer count) {
    this.count = count;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ticket)) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Objects.equals(flightId, ticket.flightId) && Objects.equals(seatType, ticket.seatType) && Objects.equals(count, ticket.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flightId, seatType, count);
  }

  @Override
  public String toString() {
    return "{" +
      " flightId='" + getFlightId() + "'" +
      ", seatType='" + getSeatType() + "'" +
      ", count='" + getCount() + "'" +
      "}";
  }
  
}
