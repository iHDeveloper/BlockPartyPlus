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
  
  public UUIDDatabase() {
    try {
      YamlConfiguration c = getUUIDYMLData();
      Object obj = c.get("uuids");
      if (obj != null && 
        obj instanceof Map)
        this.uuids = (Map<String, String>)obj; 
    } catch (NullPointerException ex) {
      this.uuids = new HashMap<>();
      Debug.error("Can't load uuids database", new Object[0]);
    } 
    if (this.uuids == null)
      this.uuids = new HashMap<>(); 
  }
  
  public UUID get(Player p) {
    try {
      UUID uuid = null;
      try {
        uuid = UUID.fromString(this.uuids.get(p.getName()));
      } catch (NullPointerException nullPointerException) {}
      if (uuid == null) {
        update(p);
        uuid = UUID.fromString(this.uuids.get(p.getName()));
      } 
      Debug.log("UUIDDatabase - Get [name='" + p.getName() + "',uuid'" + uuid + "']", new Object[0]);
      return uuid;
    } catch (NullPointerException ex) {
      ex.printStackTrace();
      return null;
    } 
  }
  
  public UUID get(String name) {
    return UUID.fromString(this.uuids.get(name));
  }
  
  public void update(Player player) {
    update(player.getPlayer());
  }
  
  public void update(Player player) {
    Player p = player;
    UUID old = null;
    UUID update = p.getUniqueId();
    try {
      try {
        old = UUID.fromString(this.uuids.get(player.getName()));
      } catch (NullPointerException nullPointerException) {}
      if (old == null) {
        this.uuids.put(p.getName(), update.toString());
      } else if (!old.equals(update)) {
        this.uuids.put(p.getName(), update.toString());
      } 
    } catch (NullPointerException ex) {
      this.uuids.put(p.getName(), update.toString());
    } 
    Debug.log("UUIDDatabase - Update [old='" + old + "',update='" + update + "']", new Object[0]);
    saveDataBase();
  }
  
  private void saveDataBase() {
    try {
      YamlConfiguration c = getUUIDYMLData();
      c.set("uuids", this.uuids);
      c.save(getUUIDYMLDataFile());
    } catch (Exception ex) {
      ex.printStackTrace();
      Debug.error("Cannot save the uuids yml data", new Object[0]);
    } 
  }
  
  private File getUUIDYMLDataFile() {
    try {
      File file = new File(iHDeveloperAPI.getDataFolder() + "/uuids.ymldata");
      file.createNewFile();
      return file;
    } catch (Exception ex) {
      return null;
    } 
  }
  
  private YamlConfiguration getUUIDYMLData() {
    try {
      YamlConfiguration c = new YamlConfiguration();
      c.load(getUUIDYMLDataFile());
      return c;
    } catch (Exception ex) {
      return null;
    } 
  }
}
