package com.welovecoding.youtube.client.model;

import java.util.HashMap;

public class Tutorials {

  private final static Tutorials instance = new Tutorials();
  private HashMap<Integer, Owner> owners = new HashMap<Integer, Owner>();
  private HashMap<Integer, Category> categories = new HashMap<Integer, Category>();
  private HashMap<Integer, Provider> providers = new HashMap<Integer, Provider>();
  private HashMap<Integer, Language> languages = new HashMap<Integer, Language>();
  private HashMap<Integer, Playlist> playlists = new HashMap<Integer, Playlist>();
//  private HashMap<Integer, Video> videos = new HashMap<Integer, Video>();

  private Tutorials() {
    super();
  }

  public static Tutorials getInstance() {
    return instance;
  }

  public void addCategory(Category category) {
    categories.put(category.getId(), category);
  }

  public void addOwner(Owner owner) {
    owners.put(owner.getId(), owner);
  }

  public void addLanguage(Language language) {
    languages.put(language.getId(), language);
  }

  public void addPlaylist(Playlist playlist) {
    playlists.put(playlist.getId(), playlist);
  }

  public void addProvider(Provider provider) {
    providers.put(provider.getId(), provider);
  }

//  public void addVideo(Video video) {
//    videos.put(video.getId(), video);
//  }
  public HashMap<Integer, Owner> getOwners() {
    return owners;
  }

  public HashMap<Integer, Category> getCategories() {
    return categories;
  }

  public HashMap<Integer, Language> getLanguages() {
    return languages;
  }

  public HashMap<Integer, Playlist> getPlaylists() {
    return playlists;
  }

  public Category getCategoryById(int id) {
    return categories.get(id);
  }

  public Playlist getPlaylistById(int id) {
    return playlists.get(id);
  }

  public Provider getProvierById(int id) {
    return providers.get(id);
  }

  public Language getLanguageById(int id) {
    return languages.get(id);
  }
//  public HashMap<Integer, Video> getVideos() {
//    return videos;
//  }
}
