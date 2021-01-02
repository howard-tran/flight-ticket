package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.IMongoDBQueryLogic;
import com.helper.PropertyHelper;
import com.model.Route;
import com.model.TicketSupplier;

import org.bson.Document;

public class RouteStatic implements IMongoDBQueryLogic {
  private static RouteStatic _instance;
  private Map<String, List<Route>> data;

  public static RouteStatic getInstance() {
    if (RouteStatic._instance == null) {
      RouteStatic._instance = new RouteStatic();
    }
    return RouteStatic._instance;
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
    
    this.data = new HashMap<String, List<Route>>();
    
    this.run(PropertyHelper.getMongoDB(), "Route", 
      collection -> {
        for (int i = 0; i < supplierIdList.size(); i++) {
          List<Route> routeList = new ArrayList<>();

          Document filter = new Document("supplier", supplierIdList.get(i));
          for (var doc : collection.find(filter)) {
            routeList.add(this.parse(doc, Route.class));
          }
          this.data.put(supplierIdList.get(i), routeList);
        }
        return null;
      });
  }

  public List<Route> getRouteData(String supplierId) {
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
