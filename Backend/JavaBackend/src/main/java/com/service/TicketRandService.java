package com.service;

import java.util.Collection;

import com.dao.IMongoDBQueryLogic;
import com.dao.ITicketDao;
import com.helper.DatabaseSupplier;
import com.helper.IConsumer;
import com.helper.PropertyHelper;
import com.model.TicketStatus;
import com.model.TicketSupplier;

import org.springframework.beans.factory.annotation.Qualifier;


public class TicketRandService implements LogService, IMongoDBQueryLogic {
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
      this.run(PropertyHelper.getMongoDB(), "Supplier", 
        collection -> {
          for (var doc : collection.find()) {
            TicketSupplier supplier = this.parse(doc, TicketSupplier.class);

            int size = TicketRandUtils.randomSize();
            for (int i = 0; i < size; i++) {
              var ticket = TicketRandUtils.random("", supplier.getName());
              ticketDao.insertTicket(ticket);

              if (TicketRandUtils.randomReverse()) {
                ticketDao.insertTicket(TicketRandUtils.reverseTicketAirline(ticket));
              }
            }
          }
          return null;
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
