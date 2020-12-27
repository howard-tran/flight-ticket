package com.service;

import com.dao.ITicketDao;
import com.helper.DatabaseSupplier;
import com.helper.IConsumer;
import com.model.TicketStatus;

import org.springframework.beans.factory.annotation.Qualifier;


public class TicketRandService implements LogService {
  private ITicketDao ticketDao;
  
  private final Long limitNewTicket = 100000000l;

  public TicketRandService(
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Ticket) ITicketDao ticketDao
  ) {
    this.ticketDao = ticketDao;
  }

  IConsumer serviceTask = () -> {
    Long newTicketCount = 0l;
    try {
      newTicketCount = ticketDao.getTicketCountByStatus(
        TicketStatus.NEW_TICKET.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (newTicketCount < this.limitNewTicket) {
      VietNamAirlineTicketService
        .requestForTickets("")
        .forEach(t -> {
          try {ticketDao.insertTicket(t);}
          catch (Exception e) {
            e.printStackTrace();
          }
        });
      
      BamBooAirlineTicketService
        .requestForTickets("")
        .forEach(t -> {
          try {ticketDao.insertTicket(t);}
          catch (Exception e) {
            e.printStackTrace();
          }
        });
    }
  };

  public void start() {
    Thread newThread = new Thread(() -> {

      while (true) {
        try {serviceTask.run();}
        catch (Exception e) {
          e.printStackTrace();
        }

        try {Thread.sleep(60l);}
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    newThread.run();
  }
}
