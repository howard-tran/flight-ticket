package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.helper.Tuple2;
import com.model.Ticket;
import com.model.TicketStatus;

public class VietNamAirlineTicketService extends TicketRandUtils {
  public static Ticket random(String agentId) {
    Tuple2<String, String> flightRand = TicketRandUtils.randomFlight();
    String seatClass = TicketRandUtils.randomSeatType();

    Ticket ticket = new Ticket(
      UUID.randomUUID().toString(),seatClass,
      flightRand.get_1(),flightRand.get_2(),
      agentId, TicketStatus.NEW_TICKET.toString(),
      "VietnamAirLine",
      TicketRandUtils.randomFlightDate().getMillis(),
      TicketRandUtils.randomPrice(seatClass)
    );
    return ticket;
  }

  public static List<Ticket> requestForTickets(String agentId) {
    int size = TicketRandUtils.randomSize();

    List<Ticket> res = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      var ticket = VietNamAirlineTicketService.random(agentId);
      res.add(ticket);

      if (TicketRandUtils.randomReverse()) {
        res.add(TicketRandUtils.reverseTicketAirline(ticket));
      }
    } 
    return res;
  }
}
