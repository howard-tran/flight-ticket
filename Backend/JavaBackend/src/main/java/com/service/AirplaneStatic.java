package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.IMongoDBQueryLogic;
import com.helper.PropertyHelper;
import com.model.Airplane;
import com.model.TicketSupplier;

import org.bson.Document;

public class AirplaneStatic implements IMongoDBQueryLogic {
  private static AirplaneStatic _instance;
  private Map<String, List<Airplane>> data;

  public static AirplaneStatic getInstance() {
    if (AirplaneStatic._instance == null) {
      AirplaneStatic._instance = new AirplaneStatic();
    }
    return AirplaneStatic._instance;
  }

  private void initData() throws Exception {
    List<String> supplierIdList = new ArrayList<>();

    this.run(PropertyHelper.getMongoDB(), "Supplier", 
      collection -> {

        for (var doc : collection.find()) {
          TicketSupplier sup = this.parse(doc, TicketSupplier.class);
          supplierIdList.add(sup.getId());
        }
        return null;
      });
    
    this.data = new HashMap<String, List<Airplane>>();
    
    this.run(PropertyHelper.getMongoDB(), "Airplane", 
      collection -> {
        for (int i = 0; i < supplierIdList.size(); i++) {
          List<Airplane> routeList = new ArrayList<>();

          Document filter = new Document("supplierId", supplierIdList.get(i));
          for (var doc : collection.find(filter)) {
            routeList.add(this.parse(doc, Airplane.class));
          }
          this.data.put(supplierIdList.get(i), routeList);
        }
        return null;
      });
  }

  public List<Airplane> getAirplaneData(String supplierId) {
    if (this.data == null) {
      try {
        this.initData();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return this.data.get(supplierId);
  }
}
