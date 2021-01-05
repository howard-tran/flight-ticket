package com;

import com.dao.FlightDao;
import com.dao.TicketDao;
import com.helper.LogUtils;
import com.service.UpdateSystemService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
  public static final String service = "/api";
  
  private static UpdateSystemService ticketRandService;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);

    ticketRandService = new UpdateSystemService(new TicketDao(), new FlightDao());
    ticketRandService.start();

    LogUtils.LogInfo("\n========== Service Restarted ==========\n", null);
  }
}
