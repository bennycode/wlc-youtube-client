package com.welovecoding.youtube.client.model;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang.StringEscapeUtils;

public class Playlist {

  private int id;
  private String code;
  private int categoryId;
  private int ownerId;
  private int providerId;
  private int languageId;
  private String title;
  private String description;
  private Map<Integer, Video> videos = new LinkedHashMap<Integer, Video>();

  public Playlist() {
  }

  public Playlist(int id, String code, int categoryId, int ownerId, int providerId, int languageId, String title, String description) {
    this.id = id;
    this.code = code;
    this.categoryId = categoryId;
    this.ownerId = ownerId;
    this.providerId = providerId;
    this.languageId = languageId;
    this.title = title;
    this.description = description;
  }

  public void addVideo(Video video) {
    videos.put(video.getId(), video);
  }

  public int getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public int getProviderId() {
    return providerId;
  }

  public int getLanguageId() {
    return languageId;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Map<Integer, Video> getVideos() {
    return videos;
  }

  @Override
  public String toString() {
    return "Playlist{" + "id=" + id + ", categoryId=" + categoryId + ", title=" + title + '}';
  }

  public String toXml() {
    Playlist playlist = this;
    String rn = System.getProperty("line.separator", "\r\n");
    String template = "        <Playlist ID=\"{0}\">" + rn
            + "          <Provider>{1}</Provider>" + rn
            + "          <CategoryID>{2}</CategoryID>" + rn
            + "          <OwnerID>{3}</OwnerID>" + rn
            + "          <Title>{4}</Title>" + rn
            + "          <Description>{5}</Description>" + rn
            + "          <Language>{6}</Language>" + rn
            + "          <Published>{7}</Published>" + rn
            + "          <Videos>" + rn;
    Object[] values = new Object[]{
      playlist.getCode(),
      Tutorials.getInstance().getProvierById(playlist.getProviderId()).getName(),
      playlist.getCategoryId(),
      playlist.getOwnerId(),
      StringEscapeUtils.escapeXml(playlist.getTitle()),
      StringEscapeUtils.escapeXml(playlist.getDescription()),
      Tutorials.getInstance().getLanguageById(playlist.getLanguageId()).getName(),
      playlist.getId()
    };
    String xml = MessageFormat.format(template, values);
    for (int key : videos.keySet()) {
      Video video = videos.get(key);
      xml += video.toXml();
    }
    xml += "          </Videos>" + rn
            + "        </Playlist>" + rn;
    return xml;
  }
}
