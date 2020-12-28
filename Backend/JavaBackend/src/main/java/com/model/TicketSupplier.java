package com.model;

import java.util.List;
import java.util.Objects;

public class TicketSupplier {
  private String name;
  private String countryCode;

  public TicketSupplier() {
  }

  public TicketSupplier(String name, String countryCode) {
    this.name = name;
    this.countryCode = countryCode;
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
        return Objects.equals(name, ticketSupplier.name) && Objects.equals(countryCode, ticketSupplier.countryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, countryCode);
  }

  @Override
  public String toString() {
    return "{" +
      " name='" + getName() + "'" +
      ", countryCode='" + getCountryCode() + "'" +
      "}";
  }

}
