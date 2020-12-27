package com.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.helper.Tuple2;
import com.model.Airline;
import com.model.Ticket;
import com.model.TicketSeatType;
import com.model.TicketStatus;

import org.joda.time.DateTime;

public class TicketRandUtils {
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

  protected static int randomSize() {
    return new Random().nextInt(51) + 50;
  }

  protected static boolean randomReverse() {
    return (new Random().nextInt(2)) == 1;
  }

  protected static int randomMinuteFlight() {
    List<Integer> arr = Arrays.asList(0,15,30,45);
    return arr.get(new Random().nextInt(arr.size()));
  } 

  protected static DateTime randomFlightDate() {
    DateTime today = DateTime.now();

    double scale = 604800.0d;
    double rand = new Random().nextDouble();
    int randSeconds = (int)((rand * scale) + 172800.0d);
    
    today = today.plusSeconds(randSeconds);
    today = today.withMinuteOfHour(TicketRandUtils.randomMinuteFlight());
    today = today.withSecondOfMinute(0);
    today = today.withMillisOfSecond(0);

    return today;
  }

  protected static Ticket reverseTicketAirline(Ticket data) {
    Ticket res = data;
    String airlineStart = res.getAirlineStart();
    res.setAirlineStart(res.getAirlineEnd());
    res.setAirlineEnd(airlineStart);
    res.setId(UUID.randomUUID().toString());

    return res;
  }
}
