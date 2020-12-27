package com.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Receipt extends MongoIdModel {
  private List<Ticket> tickets;
  private String userid;
  private String username;
  private String status;
  private String agencyId;
  private BigDecimal total;

  public Receipt() {
  }

  public Receipt(List<Ticket> tickets, String userid, String username, String status, BigDecimal total) {
    this.tickets = tickets;
    this.userid = userid;
    this.username = username;
    this.status = status;
    this.total = total;
  }

  public List<Ticket> getTickets() {
    return this.tickets;
  }

  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }

  public String getAgencyId() {
    return this.agencyId;
  }

  public void setAgencyId(String agencyId) {
    this.agencyId = agencyId;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getUserid() {
    return this.userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public BigDecimal getTotal() {
    return this.total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public Receipt tickets(List<Ticket> tickets) {
    this.tickets = tickets;
    return this;
  }

  public Receipt agencyId(String agencyId) {
    this.agencyId = agencyId;
    return this;
  }

  public Receipt userid(String userid) {
    this.userid = userid;
    return this;
  }

  public Receipt username(String username) {
    this.username = username;
    return this;
  }

  public Receipt total(BigDecimal total) {
    this.total = total;
    return this;
  }

  public Receipt status(String status) {
    this.status = status;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Receipt)) {
            return false;
        }
        Receipt receipt = (Receipt) o;
        return Objects.equals(tickets, receipt.tickets) && Objects.equals(userid, receipt.userid) && Objects.equals(username, receipt.username) && Objects.equals(total, receipt.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tickets, userid, username, total);
  }

  @Override
  public String toString() {
    return "{" +
      " tickets='" + getTickets() + "'" +
      ", userid='" + getUserid() + "'" +
      ", username='" + getUsername() + "'" +
      ", total='" + getTotal() + "'" +
      "}";
  }


}
