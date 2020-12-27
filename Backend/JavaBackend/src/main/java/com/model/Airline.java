package com.model;

import java.util.Objects;

public class Airline {
  private String id;
  private String name;
  private String countryCode;

  public Airline() {
  }

  public Airline(String id, String name, String countryCode) {
    this.id = id;
    this.name = name;
    this.countryCode = countryCode;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Airline id(String id) {
    this.id = id;
    return this;
  }

  public Airline name(String name) {
    this.name = name;
    return this;
  }

  public Airline countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }


  @Override
  public String toString() {
    return "{" +
      " id='" + id + "'" +
      ", name='" + name + "'" +
      ", countryCode='" + countryCode + "'" +
      "}";
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Airline)) {
            return false;
        }
        Airline airline = (Airline) o;
        return Objects.equals(id, airline.id) && Objects.equals(name, airline.name) && Objects.equals(countryCode, airline.countryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, countryCode);
  }
  

}
