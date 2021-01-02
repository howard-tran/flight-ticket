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
import com.helper.IFunction2;
import com.helper.IFunction3;
import com.helper.IFunction4;
import com.helper.PropertyHelper;
import com.model.Airplane;
import com.model.Flight;
import com.model.Route;
import com.model.Ticket;
import com.model.TicketSeatType;
import com.model.TicketSupplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public class TicketRandService extends TicketRandUtils implements LogService {
  private ITicketDao ticketDao;
  private IFlightDao flightDao;
  
  public TicketRandService(
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
    int ticketCount = TicketRandService.randomTicketCount(seatClass);

    for (int i = 0; i < ticketCount; i++) {
      Ticket ticket = new Ticket(flightId, "random-position-here", 
        seatClass, TicketRandService.randomPrice(seatClass));
      this.ticketDao.insertTicket(ticket);
    }
    return null;
  };

  IFunction4<String, Route, List<Airplane>, Object> createFlight = (supplier, route, airplaneList) -> {
    //
    for (var airplane: airplaneList) {
      //
      Flight flight = new Flight(airplane.getId(), supplier, 0, 
        TicketRandUtils.getWeight().get_1(), TicketRandUtils.getWeight().get_2(),
        route.getAirlineStart(), route.getAirlineEnd(), 
        TicketRandUtils.randomFlightDate());

      String flightId = this.flightDao.insertFlight(flight);

      this.createTicket.run(flightId, TicketSeatType.BUSINESS.toString());
      this.createTicket.run(flightId, TicketSeatType.PRENIUM.toString());
      this.createTicket.run(flightId, TicketSeatType.ECONOMY.toString());
    }

    return null;
  };

  IConsumer serviceTask = () -> {
    this.run(PropertyHelper.getMongoDB(), "Supplier", 
      collection -> {
        for (var doc : collection.find()) {
          TicketSupplier supplier = this.parse(doc, TicketSupplier.class);

          for (var route : RouteStatic.getInstance().getRouteData(supplier.getId())) {
            //
            this.createFlight.run(
                supplier.getId(), route,
                this.generateAirplaneList.run(supplier.getId(), 
                  TicketRandUtils.randomFlightCount())
              );
          }
        }
        return null;
      });
  };
  
  public void start() {
    Thread newThread = new Thread(() -> {

      while (true) {
        try {serviceTask.run();}
        catch (Exception e) {
          e.printStackTrace();
        }

        try {Thread.sleep(18000000l);}
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    newThread.run();
  }
}
