package me.iHDeveloper.api.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChangeEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onFoodLevelChange(FoodLevelChangeEvent e) {
    e.setFoodLevel(20);
    e.setCancelled(false);
  }
}
