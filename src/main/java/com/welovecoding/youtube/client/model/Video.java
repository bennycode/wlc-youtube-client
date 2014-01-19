package com.welovecoding.youtube.client.model;

import java.text.MessageFormat;
import java.util.Date;
import org.apache.commons.lang.StringEscapeUtils;

public class Video {

  private int id = 1;
  private int playlistId = 1;
  private String code = "";
  private String title = "";
  private String description = "";
  private Date published = new Date();

  public Video() {
  }

  public Video(int id, int playlistId, String code, String title, String description) {
    this.id = id;
    this.playlistId = id;
    this.code = code;
    this.title = title;
    //this.description = description;
  }

  public int getId() {
    return id;
  }

  public int getPlaylistId() {
    return playlistId;
  }

  public String getCode() {
    return code;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "Video{" + "id=" + id + ", title=" + title + '}';
  }

  public String toXml() {
    String rn = System.getProperty("line.separator", "\r\n");
    String template = "            <Video ID=\"{0}\">" + rn
            + "              <Title>{1}</Title>" + rn
            + "              <Description>{2}</Description>" + rn
            + "              <EmbedCode>{0}</EmbedCode>" + rn
            + "            </Video>" + rn;
    Object[] values = new Object[]{
      this.getCode(), 
      StringEscapeUtils.escapeXml(this.getTitle()), 
      StringEscapeUtils.escapeXml(this.getDescription())
    };
    String xml = MessageFormat.format(template, values);
    return xml;
  }
}
