package me.iHDeveloper.api.events;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onDeath(PlayerDeathEvent e) {
    Player p = iHDeveloperAPI.getPlayer(e.getEntity().getName());
    e.getEntity().setHealth(20.0D);
    e.setDeathMessage("");
    e.setDroppedExp(0);
    Debug.log("Player %s [name='%s',uuid='%s',killer='%s']", new Object[] { "Death", p.getName(), p.getUUID(), p.getPlayer().getKiller().getName() });
  }
}
