package com.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.dao.IMongoDBQueryLogic;
import com.helper.Tuple2;
import com.helper.UnixHelper;
import com.model.Airline;
import com.model.Ticket;
import com.model.TicketSeatType;
import com.model.TicketStatus;
import com.model.TicketType;

import org.joda.time.DateTime;

public class TicketRandUtils implements IMongoDBQueryLogic {
  protected static String randomAirline() {
    List<Airline> arr = AirlineStatic.getInstance().getData();
    return arr.get(new Random().nextInt(arr.size())).getId();
  }

  protected static Tuple2<String, String> randomFlight() {

    String _1 = TicketRandUtils.randomAirline();
    String _2 = TicketRandUtils.randomAirline();
    while (_2.equals(_1)) {
      _2 = TicketRandUtils.randomAirline();
    }
    return new Tuple2<String,String>(_1, _2);
  }

  protected static String randomSeatType() {
    List<String> arr = Arrays.asList(
      TicketSeatType.ECONOMY.toString(),
      TicketSeatType.PRENIUM.toString(),
      TicketSeatType.BUSINESS.toString()
    );
    return arr.get(new Random().nextInt(arr.size()));
  }

  protected static String randomTicketType() {
    List<String> arr = Arrays.asList(
      TicketType.ADULT.toString(),
      TicketType.MINOR.toString(),
      TicketType.BABY.toString()
    );
    return arr.get(new Random().nextInt(arr.size()));
  }

  protected static Tuple2<Float, Float> getWeight() {
    return new Tuple2<Float,Float>(7.0f, 18.0f);
  }

  protected static BigDecimal randomPrice(String seatClass) {
    double scale = 500000.0;
    double rand = new Random().nextDouble();
    double price = (rand * scale) + scale;

    if (seatClass.equals(TicketSeatType.BUSINESS.toString())) {
      price *= 1.5;
    } else if (seatClass.equals(TicketSeatType.PRENIUM.toString())) {
      price *= 1.2;
    }
    return new BigDecimal(price);
  }

  protected static int randomFlightCount() {
    return new Random().nextInt(20) + 2;
  }

  protected static int randomTicketCount(String seatClass) {
    if (seatClass.equals(TicketSeatType.BUSINESS.toString())) {
      return new Random().nextInt(11) + 1;
    } else if (seatClass.equals(TicketSeatType.PRENIUM.toString())) {
      return new Random().nextInt(21) + 1;
    } else {
      return new Random().nextInt(60) + 5;
    }
  }

  protected static boolean randomReverse() {
    return (new Random().nextInt(2)) == 1;
  }

  protected static int randomMinuteFlight() {
    List<Integer> arr = Arrays.asList(0,15,30,45);
    return arr.get(new Random().nextInt(arr.size()));
  }

  protected static Long randomFlightDate() {
    DateTime today = DateTime.now();

    double scale = 604800.0d;
    double rand = new Random().nextDouble();
    int randSeconds = (int)((rand * scale) + 172800.0d);
    
    DateTime res = new DateTime(
      UnixHelper.roundToMinute(today.plusSeconds(randSeconds), 
        TicketRandUtils.randomMinuteFlight()));
    return res.getMillis();
  }

  // protected static Ticket random(String agentId, String supplier) {
  //   Tuple2<String, String> flightRand = TicketRandUtils.randomFlight();
  //   String seatClass = TicketRandUtils.randomSeatType();
  //   String ticketType = TicketRandUtils.randomTicketType();
  //   Tuple2<Float, Float> flightWeight = TicketRandUtils.getWeight(seatClass);

  //   Ticket ticket = new Ticket(
  //     UUID.randomUUID().toString(),seatClass,
  //     ticketType,flightWeight.get_1(),flightWeight.get_2(),
  //     flightRand.get_1(),flightRand.get_2(),
  //     agentId, TicketStatus.NEW_TICKET.toString(),supplier,
  //     TicketRandUtils.randomFlightDate().getMillis(),
  //     TicketRandUtils.randomPrice(seatClass, ticketType)
  //   );
  //   return null;
  // }

  // protected static Ticket random(String agentId, String supplier, String ticketType) {
  //   Tuple2<String, String> flightRand = TicketRandUtils.randomFlight();
  //   String seatClass = TicketRandUtils.randomSeatType();
  //   Tuple2<Float, Float> flightWeight = TicketRandUtils.getWeight(seatClass);

  //   Ticket ticket = new Ticket(
  //     UUID.randomUUID().toString(),seatClass,
  //     ticketType,flightWeight.get_1(),flightWeight.get_2(),
  //     flightRand.get_1(),flightRand.get_2(),
  //     agentId, TicketStatus.NEW_TICKET.toString(),supplier,
  //     TicketRandUtils.randomFlightDate().getMillis(),
  //     TicketRandUtils.randomPrice(seatClass, ticketType)
  //   );
  //   return null;
  // }
}
