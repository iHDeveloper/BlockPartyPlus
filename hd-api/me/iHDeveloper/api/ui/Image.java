package me.iHDeveloper.api.ui;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Image extends ComponentItem implements ComponentItemEvent {
  private static final ComponentType type = ComponentType.ITEM_NONE;
  
  public Image(Material m) {
    super(type, m.getId(), 1, 0);
  }
  
  public Image(Material m, int amount) {
    super(type, m.getId(), amount, 0);
  }
  
  public Image(Material m, int amount, int data) {
    super(type, m.getId(), amount, data);
  }
  
  public Image(int id) {
    super(type, id, 1, 0);
  }
  
  public Image(int id, int amount) {
    super(type, id, amount, 0);
  }
  
  public Image(int id, int amount, int data) {
    super(type, id, amount, data);
  }
  
  public void onLoad() {
    setListener(this);
  }
  
  public void onClick(InventoryClickEvent e) {
    e.setCancelled(true);
  }
  
  public void onInteract(PlayerInteractEvent e) {}
}
