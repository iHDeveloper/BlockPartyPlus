package me.iHDeveloper.api.events;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onInteract(PlayerInteractEvent e) {
    Player p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    ItemStack item = e.getItem();
    if (item != null && 
      !item.getType().equals(Material.AIR))
      try {
        p.getForm().interact(e, p, item);
      } catch (Exception ex) {
        ex.printStackTrace();
      }  
    try {
      Debug.log("Player %s [name='%s',uuid='%s',action='%s',material='%s',item='%s']", new Object[] { "Interact", p.getName(), p.getUUID(), 
            e.getAction(), e.getMaterial(), e.getItem().getItemMeta().getDisplayName() });
    } catch (NullPointerException ex) {
      Debug.log("Player %s [name='%s',uuid='%s',action='%s',material='%s']", new Object[] { "Interact", p.getName(), p.getUUID(), 
            e.getAction(), e.getMaterial() });
    } 
  }
}
