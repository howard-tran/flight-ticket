package com.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.dao.IFlightDao;
import com.dao.ITicketDao;
import com.helper.DatabaseSupplier;
import com.helper.IConsumer;
import com.helper.IFunction;
import com.helper.IFunction2;
import com.helper.IFunction3;
import com.helper.IFunction4;
import com.helper.IFunction5;
import com.helper.LogUtils;
import com.helper.PropertyHelper;
import com.helper.UnixHelper;
import com.model.Airplane;
import com.model.Flight;
import com.model.Route;
import com.model.Ticket;
import com.model.TicketSeatType;
import com.model.TicketSupplier;
import com.mongodb.client.FindIterable;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public class UpdateSystemService extends TicketRandUtils implements LogService {
  private ITicketDao ticketDao;
  private IFlightDao flightDao;
  
  public UpdateSystemService(
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Ticket) ITicketDao ticketDao,
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Flight) IFlightDao flightDao
  ) {
    this.ticketDao = ticketDao;
    this.flightDao = flightDao;
  }

  IFunction3<Integer, Integer, Integer> compare2Field = (a, b) -> {
    if (a < b) return -1;
    if (a > b) return 1;
    return 0;
  };

  IFunction3<Long, Long, Integer> compare2Unix = (a, b) -> {
    DateTime dt_a = new DateTime(a);
    DateTime dt_b = new DateTime(b);

    Integer _1 = compare2Field.run(dt_a.getYear(), dt_b.getYear()); if (_1 != 0) return _1;
    Integer _2 = compare2Field.run(dt_a.getMonthOfYear(), dt_b.getMonthOfYear()); if (_2 != 0) return _2;
    Integer _3 = compare2Field.run(dt_a.getDayOfMonth(), dt_b.getDayOfMonth()); if (_3 != 0) return _3;
    Integer _4 = compare2Field.run(dt_a.getHourOfDay(), dt_b.getHourOfDay()); if (_4 != 0) return _4;
    Integer _5 = compare2Field.run(dt_a.getMinuteOfHour(), dt_b.getMinuteOfHour()); if (_5 != 0) return _5;
    Integer _6 = compare2Field.run(dt_a.getSecondOfMinute(), dt_b.getSecondOfMinute()); if (_6 != 0) return _6;
    Integer _7 = compare2Field.run(dt_a.getSecondOfMinute(), dt_b.getSecondOfMinute()); if (_7 != 0) return _7;
    Integer _8 = compare2Field.run(dt_a.getMillisOfSecond(), dt_b.getMillisOfSecond()); if (_8 != 0) return _8;
    return 0;
  };

  IConsumer updateTask = () -> {
    this.run(PropertyHelper.getMongoDB(), "Flight", 
      collection -> {
        FindIterable<Document> col = collection.find(new Document("isExpired", 0));

        for (var doc : col) {
          Flight flight = this.parseWithId(doc, Flight.class);
          
          Long a = DateTime.now().getMillis();
          Long b = flight.getFlightDate();
          
          if (a.compareTo(b) > 0) {
            flight.setIsExpired(1);
            
            LogUtils.LogInfo("expired flight", null);

            collection.replaceOne(new Document("_id", new ObjectId(flight.getId())), 
              this.toBsonDocumentWithId(flight));
          }
        }
        return null;
      });
  };


  
  public void start() {
    Thread updateFlightThread = new Thread(() -> {

      while (true) {
        try {updateTask.run();}
        catch (Exception e) {
          e.printStackTrace();
        }

        try {Thread.sleep(300000l);}
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    // newThread.start();
    updateFlightThread.start();
  }
}
