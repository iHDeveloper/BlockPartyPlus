package me.iHDeveloper.api.events;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.commands.HologramCommand;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onJoin(PlayerJoinEvent e) {
    HDPlayer p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    if (p != null) {
      p.setForm(p.getPlayerMainForm());
    }
    try {
      HologramCommand.updateHolograms();
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
    Debug.log("Player %s [name='%s',uuid='%s']", "Join", p.getName(), p.getUUID());
    e.setJoinMessage("");
  }
}
