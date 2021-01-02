package com.model;

import java.util.Objects;

public class Airplane {
  private String id;
  private String supplierId;
  private String model;

  public Airplane() {
  }

  public Airplane(String id, String supplierId, String model) {
    this.id = id;
    this.supplierId = supplierId;
    this.model = model;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSupplierId() {
    return this.supplierId;
  }

  public void setSupplierId(String supplierId) {
    this.supplierId = supplierId;
  }

  public String getModel() {
    return this.model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Airplane id(String id) {
    this.id = id;
    return this;
  }

  public Airplane supplierId(String supplierId) {
    this.supplierId = supplierId;
    return this;
  }

  public Airplane model(String model) {
    this.model = model;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Airplane)) {
            return false;
        }
        Airplane airplane = (Airplane) o;
        return Objects.equals(id, airplane.id) && Objects.equals(supplierId, airplane.supplierId) && Objects.equals(model, airplane.model);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, supplierId, model);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", supplierId='" + getSupplierId() + "'" +
      ", model='" + getModel() + "'" +
      "}";
  }

}
