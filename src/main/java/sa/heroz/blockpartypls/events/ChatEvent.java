package sa.heroz.blockpartypls.events;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onChat(AsyncPlayerChatEvent e) {
    Player sender = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    String message = e.getMessage();
    String msg = String.format("%s%s", new Object[] { iHDeveloperAPI.color("&f%s &7» &f", new Object[] { sender.getName() }), message });
    Bukkit.broadcastMessage(msg);
    e.setCancelled(true);
  }
}
