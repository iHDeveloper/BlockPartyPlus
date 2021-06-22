package me.iHDeveloper.api.events;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  public void onDamage(EntityDamageEvent e) {
    try {
      if (e.getEntity() instanceof Player) {
        Player p = iHDeveloperAPI.getPlayer(((Player)e.getEntity()).getName());
        Debug.log("Player %s [name='%s',uuid='%s',killer='%s']", new Object[] { "Damage", p.getName(), p.getUUID(), p.getPlayer().getKiller().getName() });
      } 
    } catch (Exception exception) {}
  }
}
