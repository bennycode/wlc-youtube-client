package com.welovecoding.youtube.client.jdbc;

import com.welovecoding.youtube.client.model.Category;
import com.welovecoding.youtube.client.model.Language;
import com.welovecoding.youtube.client.model.Owner;
import com.welovecoding.youtube.client.model.Playlist;
import com.welovecoding.youtube.client.model.Provider;
import com.welovecoding.youtube.client.model.Tutorials;
import com.welovecoding.youtube.client.model.Video;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseHandler {

  private Connection connection = null;
  private boolean isConnected = false;

  public Connection connectDebug() throws Exception {
    if (!isConnected) {
      String dbHost = "localhost";
      String dbPort = "3306";
      String dbName = "coding";
      String dbUser = "root";
      String dbPassword = "";
      // Connect to database
      String connectionURL = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      connection = DriverManager.getConnection(connectionURL, dbUser, dbPassword);
    }
    return connection;
  }

  public boolean connect() {
    if (!isConnected) {
      try {
        Context initialContext = new InitialContext();
        Context componentBindings = (Context) initialContext.lookup("java:comp/env");
        DataSource dataSource = (DataSource) componentBindings.lookup("jdbc/foo4");
        connection = dataSource.getConnection();
        isConnected = true;
      } catch (Exception ex) {
        Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return isConnected;
  }

  public void disconnect() {
    try {
      connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public List<String> getCategorieNamesForGermanUsers() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT c.title, l.name "
            + "FROM playlists p "
            + "LEFT JOIN categories c "
            + "ON p.category_id = c.id "
            + "LEFT JOIN languages l "
            + "ON p.language_id = l.id "
            + "WHERE l.id = 1 OR l.id=2 "
            + "GROUP BY c.title "
            + "ORDER BY title ASC");
    ResultSet rs = ps.executeQuery();
    ArrayList<String> names = new ArrayList<String>();

    while (rs.next()) {
      String title = rs.getString("title");
      names.add(title);
    }

    return names;
  }

  public List<String> getCategorieNamesForEnglishUsers() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT c.title, l.name FROM playlists p LEFT JOIN categories c ON p.category_id = c.id LEFT JOIN languages l ON p.language_id = l.id WHERE l.id=2 GROUP BY c.title ORDER BY title ASC");
    ResultSet rs = ps.executeQuery();
    ArrayList<String> names = new ArrayList<String>();

    while (rs.next()) {
      String title = rs.getString("title");
      names.add(title);
    }

    return names;
  }

  public List<String> getCategorieNamesAscending() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT title FROM categories ORDER BY title ASC");
    ResultSet rs = ps.executeQuery();
    ArrayList<String> names = new ArrayList<String>();

    while (rs.next()) {
      String title = rs.getString("title");
      names.add(title);
    }

    return names;
  }

  public int getNumberOfCategories() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM categories");
    ResultSet rs = ps.executeQuery();
    int number = 0;
    while (rs.next()) {
      number = rs.getInt(1);
    }
    return number;
  }

  public int getNumberOfPlaylists() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM playlists");
    ResultSet rs = ps.executeQuery();
    int number = 0;
    while (rs.next()) {
      number = rs.getInt(1);
    }
    return number;
  }

  public int getNumberOfOwners() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM owners");
    ResultSet rs = ps.executeQuery();
    int number = 0;
    while (rs.next()) {
      number = rs.getInt(1);
    }
    return number;
  }

  public int getNumberOfVideos() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM videos");
    ResultSet rs = ps.executeQuery();
    int number = 0;
    while (rs.next()) {
      number = rs.getInt(1);
    }
    return number;
  }

  public void initCategories() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT * FROM categories");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      int id = rs.getInt("id");
      String title = rs.getString("title");
      String color = rs.getString("color");
      Category category = new Category(id, title, color);
      Tutorials.getInstance().addCategory(category);
    }
  }

  public void initOwners() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT * FROM OWNERS");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      String website = rs.getString("website");
      String channelUrl = rs.getString("channel_url");
      Owner owner = new Owner(id, name, website, channelUrl);
      Tutorials.getInstance().addOwner(owner);
    }
  }

  public void initProviders() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT * FROM providers");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      Provider provider = new Provider(id, name);
      Tutorials.getInstance().addProvider(provider);
    }
  }

  public void initLanguages() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT * FROM languages");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      Language language = new Language(id, name);
      Tutorials.getInstance().addLanguage(language);
    }
  }

  public void initPlaylists() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT * FROM playlists");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      int id = rs.getInt("id");
      String code = rs.getString("code");
      int categoryId = rs.getInt("category_id");
      int ownerId = rs.getInt("owner_id");
      int providerId = rs.getInt("provider");
      int languageId = rs.getInt("language_id");
      String title = rs.getString("title");
      String description = rs.getString("description");
      Playlist playlist = new Playlist(id, code, categoryId, ownerId, providerId, languageId, title, description);
      Tutorials.getInstance().addPlaylist(playlist);
      Category category = Tutorials.getInstance().getCategoryById(categoryId);
      if (category != null) {
        category.addPlaylist(playlist);
      }
    }
  }

  public void initVideos() throws Exception {
    PreparedStatement ps = connection.prepareStatement("SELECT * FROM videos");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      int id = rs.getInt("id");
      int playlistId = rs.getInt("playlist_id");
      String code = rs.getString("code");
      String title = rs.getString("title");
      String description = rs.getString("description");
      Video video = new Video(id, playlistId, code, title, description);
      //
      Playlist playlist = Tutorials.getInstance().getPlaylistById(playlistId);
      if (playlist != null) {
        playlist.addVideo(video);
      }
      //Tutorials.getInstance().addVideo(video);
    }
  }

  public void saveCategory(Category category) {
    PreparedStatement ps = null;
    try {
      ps = connection.prepareStatement("insert into categories (title) values (?)");
      ps.setString(1, category.getTitle());
      int rowsUpdated = ps.executeUpdate();
      System.out.println("Rows updated: " + rowsUpdated);
    } catch (Exception ex) {
      Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      // Releasing all resources to avoid message from server: "Too many connections"
      try {
        ps.close();
      } catch (SQLException ex) {
        Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
      }
      try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
