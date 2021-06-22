package me.iHDeveloper.api.events;

import me.iHDeveloper.api.commands.HologramCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerWorldChangeEvent implements Listener {
  @EventHandler
  public void onWorldChange(PlayerChangedWorldEvent e) {
    HologramCommand.updateHolograms();
  }
}
