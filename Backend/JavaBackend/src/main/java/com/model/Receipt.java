package com.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Receipt extends MongoIdModel {
  private String flightId;
  private String userid;
  private String username;
  private String status;
  private Integer ticketCount;
  private Integer content;
  private Long createdDay;
  private BigDecimal total;


  public Receipt() {
  }

  public Receipt(String flightId, String userid, String username, String status, Integer ticketCount, Integer content, Long createdDay, BigDecimal total) {
    this.flightId = flightId;
    this.userid = userid;
    this.username = username;
    this.status = status;
    this.ticketCount = ticketCount;
    this.content = content;
    this.createdDay = createdDay;
    this.total = total;
  }

  public String getFlightId() {
    return this.flightId;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
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

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getTicketCount() {
    return this.ticketCount;
  }

  public void setTicketCount(Integer ticketCount) {
    this.ticketCount = ticketCount;
  }

  public Integer getContent() {
    return this.content;
  }

  public void setContent(Integer content) {
    this.content = content;
  }

  public Long getCreatedDay() {
    return this.createdDay;
  }

  public void setCreatedDay(Long createdDay) {
    this.createdDay = createdDay;
  }

  public BigDecimal getTotal() {
    return this.total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public Receipt flightId(String flightId) {
    this.flightId = flightId;
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

  public Receipt status(String status) {
    this.status = status;
    return this;
  }

  public Receipt ticketCount(Integer ticketCount) {
    this.ticketCount = ticketCount;
    return this;
  }

  public Receipt content(Integer content) {
    this.content = content;
    return this;
  }

  public Receipt createdDay(Long createdDay) {
    this.createdDay = createdDay;
    return this;
  }

  public Receipt total(BigDecimal total) {
    this.total = total;
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
        return Objects.equals(flightId, receipt.flightId) && Objects.equals(userid, receipt.userid) && Objects.equals(username, receipt.username) && Objects.equals(status, receipt.status) && Objects.equals(ticketCount, receipt.ticketCount) && Objects.equals(content, receipt.content) && Objects.equals(createdDay, receipt.createdDay) && Objects.equals(total, receipt.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flightId, userid, username, status, ticketCount, content, createdDay, total);
  }

  @Override
  public String toString() {
    return "{" +
      " flightId='" + getFlightId() + "'" +
      ", userid='" + getUserid() + "'" +
      ", username='" + getUsername() + "'" +
      ", status='" + getStatus() + "'" +
      ", ticketCount='" + getTicketCount() + "'" +
      ", content='" + getContent() + "'" +
      ", createdDay='" + getCreatedDay() + "'" +
      ", total='" + getTotal() + "'" +
      "}";
  }
  
}
