package sa.heroz.blockpartypls.events;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import sa.heroz.blockpartypls.game.Game;

public class QuitEvent implements Listener {
  @EventHandler
  public void onQuit(PlayerQuitEvent e) {
    HDPlayer p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    Game.onQuit(p);
    e.setQuitMessage("");
  }
}
