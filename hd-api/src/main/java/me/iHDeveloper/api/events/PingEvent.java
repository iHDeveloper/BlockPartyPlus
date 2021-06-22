package me.iHDeveloper.api.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPing(ServerListPingEvent e) {}
}
