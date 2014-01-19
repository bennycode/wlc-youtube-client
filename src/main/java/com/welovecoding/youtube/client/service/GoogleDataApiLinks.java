package com.welovecoding.youtube.client.service;

import com.google.gdata.data.Link;
import com.google.gdata.data.youtube.VideoEntry;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleDataApiLinks {

  public static final String GOOGLE_DATA_INTRODUCTION = "http://www.youtube.com/watch?v=ADos_xW4_J0";
  public static final String GOOGLE_DEVELOPERS_PLAYLISTS = "http://www.youtube.com/user/GoogleDevelopers/videos?view=1";

  // https://gdata.youtube.com/feeds/api/users/_x5XG1OV2P6uZZ5FSM9Ttw/playlists?v=2
  public static URL getPlayListsUrl(String userId) throws MalformedURLException {
    String url = String.format("https://gdata.youtube.com/feeds/api/users/%s/playlists?v=2", userId);
    return new URL(url);
  }

  // https://gdata.youtube.com/feeds/api/users/GoogleDevelopers/playlists?v=2
  @Deprecated
  public static URL getPlayListsUrlByUserName(String userName) throws MalformedURLException {
    String url = String.format("https://gdata.youtube.com/feeds/api/users/%s/playlists?v=2", userName);
    return new URL(url);
  }

  // https://gdata.youtube.com/feeds/api/playlists/B83C613AA955A350
  public static URL getPlayListUrl(String playListId) throws MalformedURLException {
    String url = String.format("https://gdata.youtube.com/feeds/api/playlists/%s?v=2", playListId);
    return new URL(url);
  }

  // https://www.youtube.com/view_play_list?p=B83C613AA955A350
  public static URL getPlayListViewUrl(String playListId) throws MalformedURLException {
    String url = String.format("https://www.youtube.com/view_play_list?p=%s", playListId);
    return new URL(url);
  }

  // http://gdata.youtube.com/feeds/api/videos/ADos_xW4_J0
  public static URL getVideoUrl(String videoId) throws MalformedURLException {
    String url = String.format("http://gdata.youtube.com/feeds/api/videos/%s", videoId);
    return new URL(url);
  }

  // http://www.youtube.com/watch?v=ADos_xW4_J0
  public static URL getVideoViewUrl(String videoId) throws MalformedURLException {
    String url = String.format("http://www.youtube.com/watch?v=%s", videoId);
    return new URL(url);
  }

  // B83C613AA955A350
  public static String getPlayListId(Link playListView) throws MalformedURLException {
    String playListViewLink = playListView.getHref();
    int beginOfPlayListId = playListViewLink.indexOf("?p=") + 3;
    String playListId = playListViewLink.substring(beginOfPlayListId);
    return playListId;
  }

  // https://gdata.youtube.com/feeds/api/playlists/B83C613AA955A350
  public static URL getPlayListUrl(Link playListViewLink) throws MalformedURLException {
    String playListId = getPlayListId(playListViewLink);
    URL playListUrl = getPlayListUrl(playListId);
    return playListUrl;
  }

  // https://gdata.youtube.com/feeds/api/playlists/B83C613AA955A350
  public static URL getPlayListUrlById(String playListId) throws MalformedURLException {
    String url = String.format("https://gdata.youtube.com/feeds/api/playlists/%s", playListId);
    return new URL(url);
  }

  // ADos_xW4_J0
  public static String getVideoId(VideoEntry entry) {
    String url = entry.getHtmlLink().getHref();
    int beginOfVideoId = url.indexOf("?v=") + 3;
    int endOfVideoId = url.indexOf("&");
    String videoId = url.substring(beginOfVideoId, endOfVideoId);
    return videoId;
  }
}
