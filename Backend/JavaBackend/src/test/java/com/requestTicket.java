package com;

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
import com.service.AirplaneStatic;
import com.service.RouteStatic;
import com.service.UpdateSystemService;
import com.service.TicketRandUtils;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public class requestTicket extends testCaseHandler {

  private ITicketDao ticketDao;
  private IFlightDao flightDao;
  
  public requestTicket(
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Ticket) ITicketDao ticketDao,
    @Qualifier(DatabaseSupplier.MongoDB.FlightTicket.Flight) IFlightDao flightDao
  ) {
    this.ticketDao = ticketDao;
    this.flightDao = flightDao;
  }

  IFunction3<String, Integer, List<Airplane>> generateAirplaneList = (supplier, flightCount) -> {
    Set<String> flag = new HashSet<String>();
    List<Airplane> res = new ArrayList<>();

    List<Airplane> airplaneList = AirplaneStatic.getInstance().getAirplaneData(supplier);

    while (flag.size() < flightCount) {
      int index = new Random().nextInt(airplaneList.size());
      Airplane airplane = airplaneList.get(index);

      while (flag.contains(airplane.getId())) {
        index = new Random().nextInt(airplaneList.size());
        airplane = airplaneList.get(index);
      }
      flag.add(airplane.getId());
      res.add(airplane);
    }

    return res;
  };
  
  // need generate position
  IFunction3<String, String, Object> createTicket = (flightId, seatClass) -> {
    int ticketCount = UpdateSystemService.randomTicketCount(seatClass);

    Ticket ticket = new Ticket(flightId, seatClass, ticketCount);
    this.ticketDao.insertTicket(ticket);
    return null;
  };

  IFunction5<String, Route, List<Airplane>, Long, Object> createFlight = (supplier, route, airplaneList, flightDate) -> {
    //
    for (var airplane: airplaneList) {
      //
      Flight flight = new Flight(airplane.getId(), supplier, 0, 
        TicketRandUtils.getWeight().get_1(), TicketRandUtils.getWeight().get_2(),
        route.getAirlineStart(), route.getAirlineEnd(), 
        UpdateSystemService.randomPrice(), flightDate);
      
      String flightId = this.flightDao.insertFlight(flight);

      this.createTicket.run(flightId, TicketSeatType.BUSINESS.toString());
      this.createTicket.run(flightId, TicketSeatType.PRENIUM.toString());
      this.createTicket.run(flightId, TicketSeatType.ECONOMY.toString());
    }

    return null;
  };

  IConsumer serviceTask = () -> {
    List<Long> unixTimeAdd = new ArrayList<>();
    unixTimeAdd.add(DateTime.now().getMillis() + 21600000l);

    for (int i = 0; i < 248; i++) {
      unixTimeAdd.add(unixTimeAdd.get(unixTimeAdd.size() - 1) + 21600000l);
    }

    this.run(PropertyHelper.getMongoDB(), "Supplier", 
      collection -> {

        for (int i = 0; i < unixTimeAdd.size(); i++) {
          for (var doc : collection.find()) {
            TicketSupplier supplier = this.parse(doc, TicketSupplier.class);
            
            for (var route : RouteStatic.getInstance().getRouteData(supplier.getId())) {
              //
              this.createFlight.run(
                  supplier.getId(), route,
                  this.generateAirplaneList.run(supplier.getId(), 
                    TicketRandUtils.randomFlightCount()),
                  UnixHelper.roundToMinute(unixTimeAdd.get(i), 
                    TicketRandUtils.randomMinuteFlight())
                );
            }
          }
        }
        return null;
      });
  };

  @Test
  public void entryPoint() throws Exception {
    while (true) {
      try {serviceTask.run();}
      catch (Exception e) {
        e.printStackTrace();
      }

      try {Thread.sleep(7200000l);}
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
