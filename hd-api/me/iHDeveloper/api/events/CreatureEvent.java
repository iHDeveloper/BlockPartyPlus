package me.iHDeveloper.api.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onCreature(CreatureSpawnEvent e) {
    e.setCancelled(true);
    System.out.println("Creature Spawn type: " + e.getEntityType());
    if (e.getEntityType().equals(EntityType.PLAYER))
      e.setCancelled(false); 
    if (e.getEntityType().equals(EntityType.ARMOR_STAND))
      e.setCancelled(false); 
  }
}
