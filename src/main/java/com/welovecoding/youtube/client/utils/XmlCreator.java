package com.welovecoding.youtube.client.utils;

import com.welovecoding.youtube.client.model.Category;
import com.welovecoding.youtube.client.model.Owner;
import com.welovecoding.youtube.client.model.Tutorials;
import java.util.HashMap;

public class XmlCreator {

  private final Tutorials tutorials = Tutorials.getInstance();
  private final int version = 1;

  public XmlCreator() {
  }

  public String createXml() {
    String rn = System.getProperty("line.separator", "\r\n");
    String xml = "<?xml version=\"1.0\"?>" + rn
            + "<Tutorials xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" + rn
            + "  <Version>" + version + "</Version>" + rn
            + "  <Published>2012-06-26T20:31:42</Published>" + rn;
    // Owners
    xml += "  <Owners>" + rn;
    HashMap<Integer, Owner> owners = tutorials.getOwners();
    for (int key : owners.keySet()) {
      Owner owner = owners.get(key);
      xml += owner.toXml();
    }
    xml += "  </Owners>" + rn;
    // Categories
    xml += "  <Categories>" + rn;
    HashMap<Integer, Category> categories = tutorials.getCategories();
    for (int key : categories.keySet()) {
      Category category = categories.get(key);
      xml += category.toXml();
    }
    xml += "  </Categories>" + rn;
    xml += "</Tutorials>";
    return xml;
  }
}
