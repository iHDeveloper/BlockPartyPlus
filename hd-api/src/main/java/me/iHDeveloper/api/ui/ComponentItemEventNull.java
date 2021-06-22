package me.iHDeveloper.api.ui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ComponentItemEventNull implements ComponentItemEvent {
  public void onClick(InventoryClickEvent e) {}
  
  public void onInteract(PlayerInteractEvent e) {}
}
