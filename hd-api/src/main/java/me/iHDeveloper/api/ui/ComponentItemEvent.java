package me.iHDeveloper.api.ui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface ComponentItemEvent {
  void onClick(InventoryClickEvent paramInventoryClickEvent);
  
  void onInteract(PlayerInteractEvent paramPlayerInteractEvent);
}
