package com.helper;

public class DatabaseSupplier {

  public static class MongoDB {

    public static class FlightTicket {
      private static final String prefix = "MongoDB.FlightTicket.";

      public static final String Account = prefix + "Account";
      public static final String Ticket = prefix + "Ticket";
    }
  }
}
