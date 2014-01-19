package com.welovecoding.youtube.client.utils;

import com.google.gdata.data.youtube.VideoEntry;

public class YouTubeUtils {

  /**
   * @param videoEntry
   * @return the YouTube ID of a video (Example: 2qR591lh5Ow)
   */
  public static String getYouTubeId(VideoEntry videoEntry) {
    // https://www.youtube.com/watch?v=2qR591lh5Ow&feature=youtube_gdata
    String link = videoEntry.getLinks().get(0).getHref();
    int begin = link.indexOf("?v=") + 3;
    int end = link.indexOf("&feature=youtube_gdata");
    return link.substring(begin, end);
  }

  public static String createSqlQuery(int playListId, VideoEntry videoEntry) {
    String title = videoEntry.getTitle().getPlainText();
    String code = YouTubeUtils.getYouTubeId(videoEntry);

    Object[] values = new Object[]{
      playListId,
      code,
      title,
      "NULL"
    };

    String query = String.format("INSERT INTO videos(id,playlist_id,code,title,description)VALUES(NULL,'%s','%s','%s',%s);", values);

    return query;
  }
}
