package com.welovecoding.youtube.client.service;

import com.google.gdata.client.youtube.YouTubeService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YouTube {

  private final static YouTube instance = new YouTube();
  private final YouTubeService service;

  private YouTube() {
    File propertiesFile = new File("keys/youtube.properties");
    Properties properties = new Properties();
    try {
      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(propertiesFile));
      properties.load(bis);
    } catch (IOException ex) {
      Logger.getLogger(YouTube.class.getName()).log(Level.SEVERE, null, ex);
    }

    String developerKey = properties.getProperty("developerKey");
    this.service = new YouTubeService(developerKey);
  }

  public static YouTube getInstance() {
    return instance;
  }

  public YouTubeService getService() {
    return service;
  }
}
