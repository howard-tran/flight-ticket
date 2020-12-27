package com;

import com.dao.TicketDao;
import com.helper.LogUtils;
import com.service.TicketRandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
  public static final String service = "/api";
  
  @Autowired
  private static TicketRandService ticketRandService;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);

    App.ticketRandService = new TicketRandService(new TicketDao());
    ticketRandService.start();

    LogUtils.LogInfo("\n========== Service Restarted ==========\n", null);
  }
}
