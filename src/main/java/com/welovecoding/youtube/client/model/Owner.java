package com.welovecoding.youtube.client.model;

import java.text.MessageFormat;
import org.apache.commons.lang.StringEscapeUtils;

public class Owner {

  private OwnerType type = OwnerType.Author;
  private int id = -1;
  protected String name = "";
  protected String description = "";
  protected String website = "";
  protected String channelUrl = "";
  private String imagePath = "";
  private String youTubeName = "";
  private String youTubeID = "";

  public Owner() {
    this.type = OwnerType.Undefined;
    this.id = -1;
    this.name = "";
    this.description = "";
    this.website = "";
    this.imagePath = "";
    this.youTubeName = "";
    this.youTubeID = "";
  }

  public Owner(String name) {
    this();
    this.name = name;
  }

  public Owner(String name, String youTubeName, String youTubeID, String website) {
    this();
    this.name = name;
    this.youTubeName = youTubeName;
    this.youTubeID = youTubeID;
    this.website = website;
  }

  public Owner(int id, String name, String website, String channelUrl) {
    this.id = id;
    this.name = name;
    this.website = website;
    this.channelUrl = channelUrl;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getWebsite() {
    return website;
  }

  @Override
  public String toString() {
    return "Owner{" + "id=" + id + ", name=" + name + '}';
  }

  public String toXml() {
    String rn = System.getProperty("line.separator", "\r\n");
    String template = "    <Owner ID=\"{0}\">" + rn
            + "      <Type>Undefined</Type>" + rn
            + "      <Name>{1}</Name>" + rn
            + "      <Description>{2}</Description>" + rn
            + "      <Website>{3}</Website>" + rn
            + "      <ImagePath>undefined.png</ImagePath>" + rn
            + "    </Owner>" + rn;
    Object[] values = new Object[]{
      this.getId(),
      StringEscapeUtils.escapeXml(this.getName()),
      StringEscapeUtils.escapeXml(this.getDescription()),
      this.getWebsite()
    };
    String xml = MessageFormat.format(template, values);
    return xml;
  }
}
