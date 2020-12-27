package com.service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.dao.ITicketDao;
import com.helper.DatabaseSupplier;
import com.helper.IConsumer;
import com.model.Agent;
import com.model.TicketStatus;

import org.springframework.beans.factory.annotation.Qualifier;


public class TicketRandService implements LogService {
  private ITicketDao ticketDao;
  private Timer ticketTimer;

  private final Long limitNewTicket = 1000000l;

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

  private TimerTask ticketTask = new TimerTask() {
    @Override
    public void run() {
      try {serviceTask.run();}
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  };

  public void start() {
    ticketTimer = new Timer("TicketTimer");
    ticketTimer.schedule(ticketTask, 0, 1200000l);
  }
}
