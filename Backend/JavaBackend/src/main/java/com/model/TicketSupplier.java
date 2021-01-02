package com.model;

import java.util.List;
import java.util.Objects;

public class TicketSupplier {
  private String id;
  private String name;
  private String countryCode;


  public TicketSupplier() {
  }

  public TicketSupplier(String id, String name, String countryCode) {
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

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public TicketSupplier id(String id) {
    this.id = id;
    return this;
  }

  public TicketSupplier name(String name) {
    this.name = name;
    return this;
  }

  public TicketSupplier countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TicketSupplier)) {
            return false;
        }
        TicketSupplier ticketSupplier = (TicketSupplier) o;
        return Objects.equals(id, ticketSupplier.id) && Objects.equals(name, ticketSupplier.name) && Objects.equals(countryCode, ticketSupplier.countryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, countryCode);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", countryCode='" + getCountryCode() + "'" +
      "}";
  }
  
}
