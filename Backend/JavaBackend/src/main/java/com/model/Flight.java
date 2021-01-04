package com.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Flight extends MongoIdModel {
  private String airplaneId;
  private String supplierId;
  private int isExpired;
  private float carryOn;
  private float checkInBaggage;
  private String airlineStart;
  private String airlineEnd;
  private BigDecimal price;
  private Long flightDate;


  public Flight() {
  }

  public Flight(String airplaneId, String supplierId, int isExpired, float carryOn, float checkInBaggage, String airlineStart, String airlineEnd, BigDecimal price, Long flightDate) {
    this.airplaneId = airplaneId;
    this.supplierId = supplierId;
    this.isExpired = isExpired;
    this.carryOn = carryOn;
    this.checkInBaggage = checkInBaggage;
    this.airlineStart = airlineStart;
    this.airlineEnd = airlineEnd;
    this.price = price;
    this.flightDate = flightDate;
  }

  public String getAirplaneId() {
    return this.airplaneId;
  }

  public void setAirplaneId(String airplaneId) {
    this.airplaneId = airplaneId;
  }

  public String getSupplierId() {
    return this.supplierId;
  }

  public void setSupplierId(String supplierId) {
    this.supplierId = supplierId;
  }

  public int getIsExpired() {
    return this.isExpired;
  }

  public void setIsExpired(int isExpired) {
    this.isExpired = isExpired;
  }

  public float getCarryOn() {
    return this.carryOn;
  }

  public void setCarryOn(float carryOn) {
    this.carryOn = carryOn;
  }

  public float getCheckInBaggage() {
    return this.checkInBaggage;
  }

  public void setCheckInBaggage(float checkInBaggage) {
    this.checkInBaggage = checkInBaggage;
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

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Long getFlightDate() {
    return this.flightDate;
  }

  public void setFlightDate(Long flightDate) {
    this.flightDate = flightDate;
  }

  public Flight airplaneId(String airplaneId) {
    this.airplaneId = airplaneId;
    return this;
  }

  public Flight supplierId(String supplierId) {
    this.supplierId = supplierId;
    return this;
  }

  public Flight isExpired(int isExpired) {
    this.isExpired = isExpired;
    return this;
  }

  public Flight carryOn(float carryOn) {
    this.carryOn = carryOn;
    return this;
  }

  public Flight checkInBaggage(float checkInBaggage) {
    this.checkInBaggage = checkInBaggage;
    return this;
  }

  public Flight airlineStart(String airlineStart) {
    this.airlineStart = airlineStart;
    return this;
  }

  public Flight airlineEnd(String airlineEnd) {
    this.airlineEnd = airlineEnd;
    return this;
  }

  public Flight price(BigDecimal price) {
    this.price = price;
    return this;
  }

  public Flight flightDate(Long flightDate) {
    this.flightDate = flightDate;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Flight)) {
            return false;
        }
        Flight flight = (Flight) o;
        return Objects.equals(airplaneId, flight.airplaneId) && Objects.equals(supplierId, flight.supplierId) && isExpired == flight.isExpired && carryOn == flight.carryOn && checkInBaggage == flight.checkInBaggage && Objects.equals(airlineStart, flight.airlineStart) && Objects.equals(airlineEnd, flight.airlineEnd) && Objects.equals(price, flight.price) && Objects.equals(flightDate, flight.flightDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(airplaneId, supplierId, isExpired, carryOn, checkInBaggage, airlineStart, airlineEnd, price, flightDate);
  }

  @Override
  public String toString() {
    return "{" +
      " airplaneId='" + getAirplaneId() + "'" +
      ", supplierId='" + getSupplierId() + "'" +
      ", isExpired='" + getIsExpired() + "'" +
      ", carryOn='" + getCarryOn() + "'" +
      ", checkInBaggage='" + getCheckInBaggage() + "'" +
      ", airlineStart='" + getAirlineStart() + "'" +
      ", airlineEnd='" + getAirlineEnd() + "'" +
      ", price='" + getPrice() + "'" +
      ", flightDate='" + getFlightDate() + "'" +
      "}";
  }
  
}
