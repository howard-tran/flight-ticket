package com.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.App;
import com.dao.IMongoDBQueryLogic;
import com.google.gson.Gson;
import com.helper.PropertyHelper;
import com.model.Agent;
import com.model.AgentData;

import org.bson.Document;

public class AgentStatic implements IMongoDBQueryLogic {
  private static AgentStatic _instance;
  private AgentData data;

  public static AgentStatic getInstance() {
    if (AgentStatic._instance == null) {
      AgentStatic._instance = new AgentStatic();
    }
    return AgentStatic._instance;
  }

  private void initData() throws Exception {
    this.data = new AgentData();
    
    var res = new ArrayList<Agent>();
    this.run(PropertyHelper.getMongoDB(), "Agent", 
      collection -> {
        for (var doc : collection.find()) {
          res.add(this.parse(doc, Agent.class));
        }
        this.data.setAgents(res);
        return null;
      });
  }

  public AgentData getAgentData() {
    if (this.data == null) {
      try {
        this.initData();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return this.data;
  }
}
