package com.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket extends MongoIdModel {
  private String flightId;
  private String position;
  private String seatType;
  private BigDecimal price;

  public Ticket() {
  }

  public Ticket(String flightId, String position, String seatType, BigDecimal price) {
    this.flightId = flightId;
    this.position = position;
    this.seatType = seatType;
    this.price = price;
  }

  public String getFlightId() {
    return this.flightId;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  public String getPosition() {
    return this.position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getSeatType() {
    return this.seatType;
  }

  public void setSeatType(String seatType) {
    this.seatType = seatType;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Ticket flightId(String flightId) {
    this.flightId = flightId;
    return this;
  }

  public Ticket position(String position) {
    this.position = position;
    return this;
  }

  public Ticket seatType(String seatType) {
    this.seatType = seatType;
    return this;
  }

  public Ticket price(BigDecimal price) {
    this.price = price;
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
        return Objects.equals(flightId, ticket.flightId) && Objects.equals(position, ticket.position) && Objects.equals(seatType, ticket.seatType) && Objects.equals(price, ticket.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flightId, position, seatType, price);
  }

  @Override
  public String toString() {
    return "{" +
      " flightId='" + getFlightId() + "'" +
      ", position='" + getPosition() + "'" +
      ", seatType='" + getSeatType() + "'" +
      ", price='" + getPrice() + "'" +
      "}";
  }
  

}
