package sa.heroz.blockpartypls.until;

import java.io.File;
import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class Settings {
  public static Location lobby;
  
  public static Location game;
  
  public static Location pos1;
  
  public static Location pos2;
  
  public static Location author;
  
  public static Location loading;
  
  public static Location holo_stats;
  
  public static Location holo_topKills;
  
  public static Location holo_topPoints;
  
  public static String join = "";
  
  public static String left = "";
  
  public static String eliminated = "";
  
  public static String countdown = "";
  
  public static String no_enough = "";
  
  public static String already = "";
  
  public static String full = "";
  
  public static String start_by_admin = "";
  
  public static String color = "";
  
  public static String kill = "";
  
  public static String points = "";
  
  public static String win = "";
  
  public static int maxPlayers = 6;
  
  public static void load() {
    YamlConfiguration c = (YamlConfiguration)iHDeveloperAPI.getConfig();
    loadLocations();
    join = c.getString("messages.game.join");
    left = c.getString("messages.game.left");
    eliminated = c.getString("messages.game.eliminated");
    countdown = c.getString("messages.game.countdown");
    no_enough = c.getString("messages.game.no_enough");
    already = c.getString("messages.general.already");
    full = c.getString("messages.general.full");
    color = c.getString("messages.game.color");
    start_by_admin = c.getString("messages.manage.start");
    kill = c.getString("messages.stats.kill");
    points = c.getString("messages.stats.points");
    win = c.getString("messages.game.win");
    maxPlayers = c.getInt("max-players");
  }
  
  public static void save() {
    YamlConfiguration c = (YamlConfiguration)iHDeveloperAPI.getConfig();
    c.set("lobby", lobby);
    c.set("game", game);
    c.set("floor.1", pos1);
    c.set("floor.2", pos2);
    c.set("max-players", Integer.valueOf(maxPlayers));
    c.set("author", author);
    c.set("holograms.stats", holo_stats);
    c.set("holograms.topKills", holo_topKills);
    c.set("holograms.topPoints", holo_topPoints);
  }
  
  public static Location get(YamlConfiguration c, String path) {
    Location l = null;
    Debug.log("Loading location %s...", new Object[] { path });
    try {
      l = (Location)c.get(path);
      Debug.log("Loaded location %s: %s", new Object[] { path, l.toString() });
    } catch (NullPointerException ex) {
      Debug.error("Failed to load location %s", new Object[] { path });
    } 
    return l;
  }
  
  private static void saveLocation(String path, Location location) {
    try {
      YamlConfiguration c = getLocationYMLData();
      c.set(path, location);
      c.save(getLocationYMLDataFile());
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
  }
  
  private static File getLocationYMLDataFile() {
    try {
      File file = new File(iHDeveloperAPI.getDataFolder() + "//locations.ymldata");
      if (!file.exists()) {
        file.createNewFile();
        Debug.log("&aSuccess! &eCreated Location YML-DATA File", new Object[0]);
      } 
      return file;
    } catch (Exception ex) {
      return null;
    } 
  }
  
  private static YamlConfiguration getLocationYMLData() {
    try {
      File file = getLocationYMLDataFile();
      YamlConfiguration c = new YamlConfiguration();
      c.load(file);
      return c;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    } 
  }
  
  private static void loadLocations() {
    lobby = get(getLocationYMLData(), "general.lobby");
    author = get(getLocationYMLData(), "npc.author");
    game = get(getLocationYMLData(), "game.location");
    pos1 = get(getLocationYMLData(), "game.floor.1");
    pos2 = get(getLocationYMLData(), "game.floor.2");
    loading = get(getLocationYMLData(), "game.loading");
    holo_stats = get(getLocationYMLData(), "holograms.stats");
    holo_topKills = get(getLocationYMLData(), "holograms.topKills");
    holo_topPoints = get(getLocationYMLData(), "holograms.topPoints");
  }
  
  public static void setLobby(Location location) {
    lobby = location;
    saveLocation("general.lobby", location);
  }
  
  public static void setGame(Location location) {
    game = location;
    saveLocation("game.location", location);
  }
  
  public static void setLoading(Location location) {
    loading = location;
    saveLocation("game.loading", location);
  }
  
  public static void setAuthorNPC(Location location) {
    author = location;
    saveLocation("npc.author", location);
  }
  
  public static void setPosition1(Location location) {
    pos1 = location;
    saveLocation("game.floor.1", location);
  }
  
  public static void setPosition2(Location location) {
    pos2 = location;
    saveLocation("game.floor.2", location);
  }
  
  public static void setHologramStats(Location location) {
    holo_stats = location;
    saveLocation("holograms.stats", location);
  }
  
  public static void setHologramTopKills(Location location) {
    holo_topKills = location;
    saveLocation("holograms.topKills", location);
  }
  
  public static void setHologramTopPoints(Location location) {
    holo_topPoints = location;
    saveLocation("holograms.topPoints", location);
  }
}
