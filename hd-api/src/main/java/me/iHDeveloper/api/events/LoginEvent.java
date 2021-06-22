package me.iHDeveloper.api.events;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.exceptions.APIException;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onLogin(PlayerLoginEvent e) {
    try {
      iHDeveloperAPI.loadPlayer(e.getPlayer());
    } catch (APIException ex) {
      ex.printStackTrace();
    } 
    Player p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    Debug.log("Player %s [name='%s',uuid='%s']", new Object[] { "Join", p.getName(), p.getUUID() });
  }
}
