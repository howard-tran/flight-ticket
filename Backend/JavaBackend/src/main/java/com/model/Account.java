package com.model;

import java.util.Objects;

public class Account extends MongoIdModel {
  private String username;
  private String password;
  private String email;
  private String phoneNumber;
  private String agencyId;
  private String type;

  public Account() {
  }

  public Account(String username, String password, String email, String phoneNumber, String agencyId, String type) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.agencyId = agencyId;
    this.type = type;
  }

  public Account(String username, String password, String email, String phoneNumber, String type) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.type = type;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAgencyId() {
    return this.agencyId;
  }

  public void setAgencyId(String agencyId) {
    this.agencyId = agencyId;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Account username(String username) {
    this.username = username;
    return this;
  }

  public Account password(String password) {
    this.password = password;
    return this;
  }

  public Account email(String email) {
    this.email = email;
    return this;
  }

  public Account agencyId(String agencyId) {
    this.agencyId = agencyId;
    return this;
  }

  public Account phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public Account type(String type) {
    this.type = type;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(username, account.username) && Objects.equals(password, account.password) && Objects.equals(email, account.email) && Objects.equals(phoneNumber, account.phoneNumber) && Objects.equals(agencyId, account.agencyId) && Objects.equals(type, account.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, email, phoneNumber, agencyId, type);
  }

  @Override
  public String toString() {
    return "{" +
      " username='" + getUsername() + "'" +
      ", password='" + getPassword() + "'" +
      ", email='" + getEmail() + "'" +
      ", phoneNumber='" + getPhoneNumber() + "'" +
      ", agencyId='" + getAgencyId() + "'" +
      ", type='" + getType() + "'" +
      "}";
  }


}
