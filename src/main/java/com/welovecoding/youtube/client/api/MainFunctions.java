package com.welovecoding.youtube.client.api;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.ServiceException;
import com.welovecoding.youtube.client.jdbc.DatabaseHandler;
import com.welovecoding.youtube.client.service.GoogleDataApiLinks;
import com.welovecoding.youtube.client.service.YouTube;
import com.welovecoding.youtube.client.utils.FileUtilities;
import com.welovecoding.youtube.client.utils.XmlCreator;
import com.welovecoding.youtube.client.utils.YouTubeUtils;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFunctions {

  private final YouTubeService service = YouTube.getInstance().getService();
  private static final Logger LOG = Logger.getLogger(MainFunctions.class.getName());

  public static void createXmlForWindowsStoreApp() throws Exception {
    DatabaseHandler db = new DatabaseHandler();
    db.connectDebug();
    db.initOwners();
    db.initProviders();
    db.initCategories();
    db.initLanguages();
    db.initPlaylists();
    db.initVideos();

    XmlCreator xmlCreator = new XmlCreator();
    String xml = xmlCreator.createXml();
    System.out.println(xml);

    File xmlFile = new File("tutorials.xml");
    FileUtilities.writeStringToFile(xml, xmlFile);
  }

  private void getVideoFeed(URL playListUrl, int playlistId) throws MalformedURLException, IOException, ServiceException {
    VideoFeed videoFeed = service.getFeed(playListUrl, VideoFeed.class);
    List<VideoEntry> entries = videoFeed.getEntries();

    // LOG.log(Level.INFO, "Fetched {0} videos...", entries.size());

    // Prints only 20 videos
    for (VideoEntry videoEntry : entries) {
      String query = YouTubeUtils.createSqlQuery(playlistId, videoEntry);
      System.out.println(query);
    }
    // Next videos...
    if (videoFeed.getNextLink() != null) {
      URL nextPlayListUrl = new URL(videoFeed.getNextLink().getHref());
      this.getVideoFeed(nextPlayListUrl, playlistId);
    }
  }

  public void getVidoesFromPlaylist(int customPlayListId, String playListId) throws Exception {

    int playlistId = customPlayListId;
    String playlistCode = playListId;
    URL playListUrl = GoogleDataApiLinks.getPlayListUrlById(playlistCode);

    LOG.log(Level.INFO, "YouTube API URL for playlist:\r\n{0}", playListUrl.getPath());

    getVideoFeed(playListUrl, playlistId);
  }

  public void getAllVideosFromAuthor(String userName, int customPlaylistId) throws Exception {
    URL feedUrl = new URL("https://gdata.youtube.com/feeds/api/users/" + userName + "/uploads");
    VideoFeed feed = service.getFeed(feedUrl, VideoFeed.class);
    getVideoFeed(feedUrl, customPlaylistId);
  }

}
