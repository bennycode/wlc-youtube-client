package com.welovecoding.youtube.client.model;

import java.text.MessageFormat;
import java.util.HashMap;
import org.apache.commons.lang.StringEscapeUtils;

public class Category {

  private int id = -1;
  private String title = "";
  private String color = "#FFFFFF";
  private HashMap<Integer, Playlist> playlists = new HashMap<Integer, Playlist>();

  public Category() {
    this.title = "";
  }

  public Category(int id, String title, String color) {
    this.id = id;
    this.title = title;
    this.color = color;
  }

  public void addPlaylist(Playlist playlist) {
    playlists.put(playlist.getId(), playlist);
  }

  public HashMap<Integer, Playlist> getPlaylists() {
    return playlists;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public String getColor() {
    return color;
  }

  public void setTitle(String name) {
    this.title = name;
  }

  @Override
  public String toString() {
    return "Category{" + "id=" + id + ", title=" + title + '}';
  }

  public String toXml() {
    Category category = this;
    String rn = System.getProperty("line.separator", "\r\n");
    String template = "    <Category ID=\"{0}\">" + rn
            + "      <Title>{1}</Title>" + rn
            + "      <Color>{2}</Color>" + rn
            + "      <ImagePath>undefined.png</ImagePath>" + rn;
    Object[] values = new Object[]{
      category.getId(),
      StringEscapeUtils.escapeXml(category.getTitle()),
      category.getColor()
    };
    String xml = MessageFormat.format(template, values);
    // Playlists
    xml += "      <Playlists>" + rn;
    for (int key : playlists.keySet()) {
      Playlist playlist = playlists.get(key);
      xml += playlist.toXml();
    }
    xml += "      </Playlists>" + rn
            + "    </Category>" + rn;
    return xml;
  }
}
