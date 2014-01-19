package com.welovecoding.youtube.client.model;

import java.text.MessageFormat;

public class Provider {

  private int id = -1;
  private String name = "";

  public Provider(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }
  
  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Provider{" + "id=" + id + ", name=" + name + '}';
  }    
}
