package me.iHDeveloper.api.ui;

import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class Folder extends ComponentItem {
  private static final ComponentType type = ComponentType.FOLDER;
  
  public Folder(Material m) {
    super(type, m.getId(), 1, 0);
  }
  
  public Folder(Material m, int amount) {
    super(type, m.getId(), amount, 0);
  }
  
  public Folder(Material m, int amount, int data) {
    super(type, m.getId(), amount, data);
  }
  
  public Folder(int id) {
    super(type, id, 1, 0);
  }
  
  public Folder(int id, int amount) {
    super(type, id, amount, 0);
  }
  
  public Folder(int id, int amount, int data) {
    super(type, id, amount, data);
  }
  
  public void onLoad() {
    setListener(new ComponentItemEventNull());
  }
  
  public void setName(String name) {
    ItemMeta meta = getMeta();
    meta.setDisplayName(iHDeveloperAPI.color(name, new Object[0]));
    getItem().setItemMeta(meta);
  }
  
  public void setNumber(int number) {
    getItem().setAmount(number);
  }
}
