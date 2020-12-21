package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.helper.Tuple2;
import com.model.Ticket;
import com.model.TicketStatus;

public class BamBooAirlineTicketService extends TicketService {
  public static Ticket random(String agentId) {
    Tuple2<String, String> flightRand = TicketService.randomFlight();
    String seatClass = TicketService.randomSeatType();

    Ticket ticket = new Ticket(
      UUID.randomUUID().toString(),seatClass,
      flightRand.get_1(),flightRand.get_2(),
      agentId,
      TicketStatus.NEW_TICKET.toString(),
      "BambooAirline",
      TicketService.randomFlightDate().getMillis(),
      TicketService.randomPrice(seatClass)
    );
    return ticket;
  }

  public static List<Ticket> requestForTickets(String agentId) {
    int size = TicketService.randomSize();

    List<Ticket> res = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      var ticket = BamBooAirlineTicketService.random(agentId);
      res.add(ticket);

      if (TicketService.randomReverse()) {
        res.add(TicketService.reverseTicketAirline(ticket));
      }
    } 
    return res;
  }
}
