
package com.welovecoding.youtube.client.model;


public class Language {
  private int id = -1;
  private String name  = null;
  
  public Language(){
  }
  
  public Language(int id, String name){
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Language{" + "id=" + id + ", name=" + name + '}';
  }
}
