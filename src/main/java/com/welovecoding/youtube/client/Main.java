package com.welovecoding.youtube.client;

import com.welovecoding.youtube.client.api.MainFunctions;

public class Main {

  public static void main(String[] args) throws Exception {
    String database = "coding";
    String playlistId = "55";
    String playlistCode = "SP5-HGvYNkCrUMVJxw3TXxhu6DJSO2KyU4";
    String category_id = "22";
    String owner_id = "27";
    String provider_id = "1";
    String language_id = "1";
    String title = "Datenbanken 1";
    String slug = "datenbanken-1";
    String description = "";

    Object[] playlistValues = new Object[]{
      playlistId,
      playlistCode,
      category_id,
      owner_id,
      provider_id,
      language_id,
      title,
      slug,
      description
    };

    Object[] ownerValues = new Object[]{
      owner_id,
      "Prof. Dr. Thomas Preuss",
      "http://www.youtube.com/user/DatenbankenFHB"
    };

    // New string:
    // INSERT INTO `coding`.`OWNER` (`ID`, `CHANNEL_URL`, `ENABLED`, `NAME`) VALUES ('27', 'http://www.youtube.com/user/DatenbankenFHB', '1', 'Prof. Dr. Thomas Preuss');
    // Old string:
    String createOwner_old
            = String.format("INSERT INTO `owners` (`id` ,`type` ,`name` ,`description` ,`website` ,`channel_url`) VALUES\n"
                    + "('%s', NULL , '%s', NULL , NULL, '%s');", ownerValues);

    // New string:
    // INSERT INTO `coding`.`PLAYLIST` (`ID`, `CODE`, `ENABLED`, `SLUG`, `TITLE`, `CATEGORY_ID`, `LANGUAGEENTRY_ID`, `OWNER_ID`, `PROVIDER_ID`) VALUES ('55', 'SP5-HGvYNkCrUMVJxw3TXxhu6DJSO2KyU4', '1', 'datenbanken-1', 'Datenbanken 1', '22', '1', '27', '1');
    // Old string:
    String createPlaylist
            = String.format("INSERT INTO `playlists` (`id`,`code`,`category_id`,`owner_id`,`provider_id`,`language_id`,`title`,`slug`,`description`) VALUES\n"
                    + "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');", playlistValues);

    System.out.println(createOwner_old);
    System.out.println(createPlaylist);

    MainFunctions main = new MainFunctions();
    main.getVidoesFromPlaylist(Integer.valueOf(playlistId), playlistCode);

    // Activate category, playlist & videos
    for (int i = 1588; i < 1681; i++) {
      System.out.println("INSERT INTO `" + database + "`.`VIDEO_CHANNEL` (`VIDEO_ID`, `CHANNEL`) VALUES ('" + String.valueOf(i) + "', 'WLC');");
      System.out.println("INSERT INTO `" + database + "`.`VIDEO_CHANNEL` (`VIDEO_ID`, `CHANNEL`) VALUES ('" + String.valueOf(i) + "', 'FHB');");
    }
  }

}
