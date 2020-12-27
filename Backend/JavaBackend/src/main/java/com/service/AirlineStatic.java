package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.IMongoDBQueryLogic;
import com.helper.PropertyHelper;
import com.model.Airline;


public class AirlineStatic implements IMongoDBQueryLogic {
  private static AirlineStatic _instance;
  private List<Airline> data;

  public static AirlineStatic getInstance() {
    if (AirlineStatic._instance == null) {
      AirlineStatic._instance = new AirlineStatic();
    }
    return AirlineStatic._instance;
  }

  private void initData() throws Exception {
    this.data = new ArrayList<Airline>();
    
    this.run(PropertyHelper.getMongoDB(), "Airline", 
      collection -> {
        for (var doc : collection.find()) {
          this.data.add(this.parse(doc, Airline.class));
        }
        return null;
      });
  }

  public List<Airline> getData() {
    if (this.data == null) {
      try {this.initData();}
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    return this.data;
  }
}
