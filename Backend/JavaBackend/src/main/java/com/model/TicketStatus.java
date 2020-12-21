package com.model;

public enum TicketStatus {
  NEW_TICKET("NEW_TICKET"),
  SOLD_TICKET("SOLD_TICKET"),
  PENDING_TICKET("PENDING_TICKET");
  
  private String _data;

  TicketStatus(String data) {
    this._data = data;
  }

  @Override
  public String toString() {
    return this._data;
  }
}
