package me.iHDeveloper.api.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onChat(AsyncPlayerChatEvent e) {}
}
