package me.iHDeveloper.api.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.hologram.Hologram;
import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

class Manager {
  static Map<String, Hologram> holograms = new HashMap<>();
  
  static void create(String id, Location location, double distance) throws IllegalArgumentException, NullPointerException {
    if (distance <= 0.0D)
      throw new IllegalArgumentException("The distance must be more than 0.0"); 
    if (location == null)
      throw new NullPointerException("The hologram location is null"); 
    if (holograms.containsKey(id))
      throw new IllegalArgumentException("This id is already used!"); 
    Hologram hologram = new Hologram(location, distance);
    hologram.addText(id);
    hologram.update();
    holograms.put(id, hologram);
  }
  
  static void delete(String id) throws IllegalArgumentException {
    Hologram hologram = getHologram(id);
    hologram.getTextList().clear();
    hologram.update();
    hologram.hideAll();
    holograms.remove(id);
    try {
      File f = new File(iHDeveloperAPI.getDataFolder() + "//holograms.ymldata");
      YamlConfiguration c = new YamlConfiguration();
      f.createNewFile();
      c.load(f);
      c.set("hologram." + id, null);
      c.save(f);
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
  }
  
  static void editDistance(String id, double distance) throws IllegalArgumentException {
    if (distance <= 0.0D)
      throw new IllegalArgumentException("The distance must be more than 0.0"); 
    Hologram hologram = getHologram(id);
    hologram.setDistance(distance);
    hologram.update();
  }
  
  static void addText(String id, String text) throws IllegalArgumentException {
    Hologram hologram = getHologram(id);
    hologram.addText(iHDeveloperAPI.color(text, new Object[0]));
    hologram.update();
  }
  
  static void editText(String id, int number, String text) throws IllegalArgumentException {
    Hologram hologram = getHologram(id);
    if (hologram.getTextList().size() <= number)
      throw new IllegalArgumentException("Not found the line with id " + number); 
    hologram.getTextList().set(number, iHDeveloperAPI.color(text, new Object[0]));
    hologram.update();
  }
  
  static void removeText(String id, int number) throws IllegalArgumentException {
    Hologram hologram = getHologram(id);
    if (hologram.getTextList().size() <= number)
      throw new IllegalArgumentException("Not found the line with id " + number); 
    hologram.getTextList().remove(number);
    hologram.update();
  }
  
  static Hologram getHologram(String id) throws IllegalArgumentException {
    if (!holograms.containsKey(id))
      throw new IllegalArgumentException("Not found the id"); 
    Hologram hologram = holograms.get(id);
    return hologram;
  }
  
  static void loadAll() {
    try {
      File f = new File(iHDeveloperAPI.getDataFolder() + "//holograms.ymldata");
      YamlConfiguration c = new YamlConfiguration();
      f.createNewFile();
      c.load(f);
      String holograms = "holograms";
      List<String> list = c.getStringList("holograms");
      Debug.log("Loading %s holograms...", new Object[] { Integer.valueOf(list.size()) });
      for (String id : list) {
        Debug.log("Loading %s hologram...", new Object[] { id });
        load(id);
        Debug.log("Loaded %s hologram: %s", new Object[] { id, Manager.holograms.get(id) });
      } 
      Debug.log("Loaded %s holograms!", new Object[] { Integer.valueOf(list.size()) });
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
  }
  
  static void load(String id) {
    String pHologram = "hologram." + id + ".";
    String pDistance = String.valueOf(pHologram) + "distance";
    String pTexts = String.valueOf(pHologram) + "texts";
    String pLocation = String.valueOf(pHologram) + "location";
    try {
      File f = new File(iHDeveloperAPI.getDataFolder() + "//holograms.ymldata");
      YamlConfiguration c = new YamlConfiguration();
      f.createNewFile();
      c.load(f);
      Location location = (Location)c.get(pLocation);
      double distance = c.getDouble(pDistance);
      List<String> texts = c.getStringList(pTexts);
      Hologram h = new Hologram(location, distance);
      for (String text : texts)
        h.addText(iHDeveloperAPI.color(text, new Object[0])); 
      holograms.put(id, h);
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
  }
  
  static void saveAll() {
    try {
      File f = new File(iHDeveloperAPI.getDataFolder() + "//holograms.ymldata");
      YamlConfiguration c = new YamlConfiguration();
      f.createNewFile();
      c.load(f);
      String holograms = "holograms";
      List<String> list = new ArrayList<>();
      for (String id : Manager.holograms.keySet())
        list.add(id); 
      c.set("holograms", list);
      c.save(f);
      for (String id : Manager.holograms.keySet())
        save(id); 
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
  }
  
  static void save(String id) {
    try {
      String pHologram = "hologram." + id + ".";
      String pDistance = String.valueOf(pHologram) + "distance";
      String pTexts = String.valueOf(pHologram) + "texts";
      String pLocation = String.valueOf(pHologram) + "location";
      File f = new File(iHDeveloperAPI.getDataFolder() + "//holograms.ymldata");
      YamlConfiguration c = new YamlConfiguration();
      f.createNewFile();
      c.load(f);
      Hologram h = getHologram(id);
      c.set(pLocation, h.getLocation());
      c.set(pDistance, Double.valueOf(h.getDistance()));
      c.set(pTexts, h.getTextList());
      c.save(f);
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
  }
}
