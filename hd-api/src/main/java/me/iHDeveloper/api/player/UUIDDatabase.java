package me.iHDeveloper.api.player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class UUIDDatabase {
  private Map<String, String> uuids;

  @SuppressWarnings("unchecked")
  public UUIDDatabase() {
    try {
      YamlConfiguration c = getUUIDYMLData();
      Object obj = c.get("uuids");
      if (obj instanceof Map) {
        this.uuids = (Map<String, String>) obj;
      }
    } catch (NullPointerException ex) {
      this.uuids = new HashMap<>();
      Debug.error("Can't load uuids database");
    } 
    if (this.uuids == null)
      this.uuids = new HashMap<>(); 
  }
  
  public UUID get(Player p) {
    try {
      UUID uuid = null;
      try {
        uuid = UUID.fromString(this.uuids.get(p.getName()));
      } catch (NullPointerException exception) {
        exception.printStackTrace();
      }
      if (uuid == null) {
        update(p);
        uuid = UUID.fromString(this.uuids.get(p.getName()));
      } 
      Debug.log("UUIDDatabase - Get [name='" + p.getName() + "',uuid'" + uuid + "']");
      return uuid;
    } catch (NullPointerException ex) {
      ex.printStackTrace();
      return null;
    } 
  }
  
  public UUID get(String name) {
    return UUID.fromString(this.uuids.get(name));
  }
  
  public void update(HDPlayer player) {
    update(player.getPlayer());
  }
  
  public void update(Player player) {
    UUID old = null;
    UUID update = player.getUniqueId();
    try {
      try {
        old = UUID.fromString(this.uuids.get(player.getName()));
      } catch (NullPointerException exception) {
        exception.printStackTrace();
      }
      if (old == null) {
        this.uuids.put(player.getName(), update.toString());
      } else if (!old.equals(update)) {
        this.uuids.put(player.getName(), update.toString());
      } 
    } catch (NullPointerException ex) {
      this.uuids.put(player.getName(), update.toString());
    } 
    Debug.log("UUIDDatabase - Update [old='" + old + "',update='" + update + "']");
    saveDataBase();
  }
  
  private void saveDataBase() {
    try {
      YamlConfiguration c = getUUIDYMLData();
      c.set("uuids", this.uuids);
      c.save(getUUIDYMLDataFile());
    } catch (Exception ex) {
      ex.printStackTrace();
      Debug.error("Cannot save the uuids yml data");
    } 
  }
  
  private File getUUIDYMLDataFile() {
    return new File(iHDeveloperAPI.getDataFolder() + "/uuids.ymldata");
  }
  
  private YamlConfiguration getUUIDYMLData() {
    YamlConfiguration c = new YamlConfiguration();
    try {
      File dataFile = getUUIDYMLDataFile();
      if (!dataFile.createNewFile())
        c.load(dataFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return c;
  }
}
