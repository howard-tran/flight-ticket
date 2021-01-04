package com.model;

import java.util.Objects;

public class TicketProfile extends MongoIdModel {
  private String ticketId;
  private Long updateStatusDate;
  
  // prefix = "#TICKETPROFILE#"
  private String updateContent; 

  public TicketProfile() {
  }

  public TicketProfile(String ticketId, Long updateStatusDate, String updateContent) {
    this.ticketId = ticketId;
    this.updateStatusDate = updateStatusDate;
    this.updateContent = updateContent;
  }

  public String getTicketId() {
    return this.ticketId;
  }

  public void setTicketId(String ticketId) {
    this.ticketId = ticketId;
  }

  public Long getUpdateStatusDate() {
    return this.updateStatusDate;
  }

  public void setUpdateStatusDate(Long updateStatusDate) {
    this.updateStatusDate = updateStatusDate;
  }

  public String getUpdateContent() {
    return this.updateContent;
  }

  public void setUpdateContent(String updateContent) {
    this.updateContent = updateContent;
  }

  public TicketProfile ticketId(String ticketId) {
    this.ticketId = ticketId;
    return this;
  }

  public TicketProfile updateStatusDate(Long updateStatusDate) {
    this.updateStatusDate = updateStatusDate;
    return this;
  }

  public TicketProfile updateContent(String updateContent) {
    this.updateContent = updateContent;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TicketProfile)) {
            return false;
        }
        TicketProfile ticketProfile = (TicketProfile) o;
        return Objects.equals(ticketId, ticketProfile.ticketId) && Objects.equals(updateStatusDate, ticketProfile.updateStatusDate) && Objects.equals(updateContent, ticketProfile.updateContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticketId, updateStatusDate, updateContent);
  }

  @Override
  public String toString() {
    return "{" +
      " ticketId='" + getTicketId() + "'" +
      ", updateStatusDate='" + getUpdateStatusDate() + "'" +
      ", updateContent='" + getUpdateContent() + "'" +
      "}";
  }

}
