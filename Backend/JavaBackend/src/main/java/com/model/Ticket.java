package com.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket extends MongoIdModel {
  private String ticketId;
  private String seatType;
  private String airlineStart;
  private String airlineEnd;
  private String agencyId;
  private String status;
  private String suplier;
  private Long flightDate;
  private BigDecimal price;

  public Ticket() {
  }

  public Ticket(String ticketId, String seatType, String airlineStart, String airlineEnd, String agencyId, String status, String suplier, Long flightDate, BigDecimal price) {
    this.ticketId = ticketId;
    this.seatType = seatType;
    this.airlineStart = airlineStart;
    this.airlineEnd = airlineEnd;
    this.agencyId = agencyId;
    this.suplier = suplier;
    this.status = status;
    this.flightDate = flightDate;
    this.price = price;
  }

  public String getTicketId() {
    return this.ticketId;
  }

  public void setTicketId(String ticketId) {
    this.ticketId = ticketId;
  }

  public String getSuplier() {
    return this.suplier;
  }

  public void setSuplier(String suplier) {
    this.suplier = suplier;
  }

  public Long getFlightDate() {
    return this.flightDate;
  }

  public void setFlightDate(Long flightDate) {
    this.flightDate = flightDate;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getSeatType() {
    return this.seatType;
  }

  public void setSeatType(String seatType) {
    this.seatType = seatType;
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

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getAgencyId() {
    return this.agencyId;
  }

  public void setAgencyId(String agencyId) {
    this.agencyId = agencyId;
  }

  public Ticket ticketId(String ticketId) {
    this.ticketId = ticketId;
    return this;
  }

  public Ticket seatType(String seatType) {
    this.seatType = seatType;
    return this;
  }

  public Ticket airlineStart(String airlineStart) {
    this.airlineStart = airlineStart;
    return this;
  }

  public Ticket airlineEnd(String airlineEnd) {
    this.airlineEnd = airlineEnd;
    return this;
  }

  public Ticket status(String status) {
    this.status = status;
    return this;
  }

  public Ticket suplier(String suplier) {
    this.suplier = suplier;
    return this;
  }

  public Ticket flightDate(Long flightDate) {
    this.flightDate = flightDate;
    return this;
  }

  public Ticket price(BigDecimal price) {
    this.price = price;
    return this;
  }

  public Ticket agencyId(String agencyId) {
    this.agencyId = agencyId;
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
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(seatType, ticket.seatType) && Objects.equals(airlineStart, ticket.airlineStart) && Objects.equals(airlineEnd, ticket.airlineEnd) && Objects.equals(agencyId, ticket.agencyId) && Objects.equals(status, ticket.status) && Objects.equals(suplier, ticket.suplier) && Objects.equals(flightDate, ticket.flightDate) && Objects.equals(price, ticket.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticketId, seatType, airlineStart, airlineEnd, agencyId, status, suplier, flightDate, price);
  }

  @Override
  public String toString() {
    return "{" +
      " ticketId='" + getTicketId() + "'" +
      ", seatType='" + getSeatType() + "'" +
      ", airlineStart='" + getAirlineStart() + "'" +
      ", airlineEnd='" + getAirlineEnd() + "'" +
      ", agencyId='" + getAgencyId() + "'" +
      ", status='" + getStatus() + "'" +
      ", suplier='" + getSuplier() + "'" +
      ", flightDate='" + getFlightDate() + "'" +
      ", price='" + getPrice() + "'" +
      "}";
  }

}
