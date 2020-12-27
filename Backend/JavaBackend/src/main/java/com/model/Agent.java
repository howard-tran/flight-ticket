package com.model;

import java.util.Objects;

public class Agent {
  private String id;
  private String name;
  private String address;
  private String countryCode;

  public Agent() {
  }

  public Agent(String id, String name, String address, String countryCode) {
    this.id = id;
    this.name = name;
    this.address = address;
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

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public Agent id(String id) {
    this.id = id;
    return this;
  }

  public Agent name(String name) {
    this.name = name;
    return this;
  }

  public Agent address(String address) {
    this.address = address;
    return this;
  }

  public Agent countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Agent)) {
            return false;
        }
        Agent agent = (Agent) o;
        return Objects.equals(id, agent.id) && Objects.equals(name, agent.name) && Objects.equals(address, agent.address) && Objects.equals(countryCode, agent.countryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, address, countryCode);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", address='" + getAddress() + "'" +
      ", countryCode='" + getCountryCode() + "'" +
      "}";
  }
}
