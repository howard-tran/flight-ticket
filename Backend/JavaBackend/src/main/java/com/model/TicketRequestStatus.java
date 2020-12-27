package com.model;

public enum TicketRequestStatus {
  PENDING("PENDING"),
  NEW("NEW"),
  EXPIRED("EXPIRED");

  private String _data;

  TicketRequestStatus(String data) {
    this._data = data;
  }

  @Override
  public String toString() {
    return this._data;
  }
}
