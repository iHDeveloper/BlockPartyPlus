package me.iHDeveloper.api.events;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onQuit(PlayerQuitEvent e) {
    HDPlayer p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    Debug.log("Player %s [name='%s',uuid='%s']", "Quit", p.getName(), p.getUUID());
    e.setQuitMessage("");
  }
}
