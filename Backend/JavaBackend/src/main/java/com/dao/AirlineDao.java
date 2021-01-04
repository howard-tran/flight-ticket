package com.dao;

import java.util.Properties;

import com.helper.DatabaseSupplier;
import com.helper.LogUtils;
import com.helper.PropertyHelper;
import com.model.Airline;

import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository(DatabaseSupplier.MongoDB.FlightTicket.Airline)
public class AirlineDao implements IAirlineDao {
  @Override
  public Airline getAirline(String id) throws Exception {
    return this.run(PropertyHelper.getMongoDB(), "Airline", 
      collection -> {
        Document filter = new Document("id", id);
        Document res = collection.find(filter).first();

        if (res == null) {
          LogUtils.LogInfo("fuck airline dao", null);
          return null;
        }
        return this.parse(res, Airline.class);
      });
  }
}
