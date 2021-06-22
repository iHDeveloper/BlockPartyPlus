package me.iHDeveloper.api.events;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class KickEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onKick(PlayerKickEvent e) {
    Player p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    Debug.log("Player %s [name='%s',uuid='%s',reason='%s',leaveMessage='%s']", new Object[] { "Kick", p.getName(), p.getUUID(), e.getReason(), e.getLeaveMessage() });
    e.setLeaveMessage("");
  }
}
