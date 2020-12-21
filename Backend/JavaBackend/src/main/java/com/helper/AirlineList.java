package com.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.App;
import com.google.gson.Gson;
import com.model.Airline;
import com.model.AirlineData;

public class AirlineList {
  private static List<Airline> data;

  private static void initData() throws Exception {
    data = new ArrayList<>();
    
    String json = "";

    String filePath = App.class.getResource("/airline.json").getFile();
    BufferedReader br = new BufferedReader(new FileReader(filePath));
    
    while (true) {
      String t = br.readLine();
      if (t != null) {
        json += t;
      } else break;
    }

    AirlineData obj = new Gson().fromJson(json, AirlineData.class);
    List<Airline> arr = obj.getAirlines();
    for (int i = 0; i < arr.size(); i++) {
      data.add(arr.get(i));
    }
  }

  public static String random() {
    if (data == null) {
      try {
        AirlineList.initData();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return AirlineList.data.get(
      new Random().nextInt(AirlineList.data.size())).getId();
  }
}
