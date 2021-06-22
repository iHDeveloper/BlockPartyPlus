package me.iHDeveloper.api.events;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.commands.HologramCommand;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onJoin(PlayerJoinEvent e) {
    Player p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    p.setForm(p.getPlayerMainForm());
    try {
      HologramCommand.updateHolograms();
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
    Debug.log("Player %s [name='%s',uuid='%s']", new Object[] { "Join", p.getName(), p.getUUID() });
    e.setJoinMessage("");
  }
}
