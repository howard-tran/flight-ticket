package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.bson.types.ObjectId;

/**
 * _id = mongodb ObjectId
 * id = id by string
 */
public class MongoIdModel {
  @JsonIgnore
  protected ObjectId _id;

  protected String id;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }
  
  public ObjectId get_id() {
    return this._id;
  }

  public void set_id(ObjectId _id) {
    this._id = _id;
    this.setId(_id.toString());
  }

  public MongoIdModel id(String id) {
    this.id = id;
    return this;
  }

  public MongoIdModel _id(ObjectId _id) {
    this._id = _id;
    return this;
  }
}
