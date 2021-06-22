package sa.heroz.blockpartypls.events;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.game.GameState;
import sa.heroz.blockpartypls.until.Settings;
import sa.heroz.blockpartypls.until.TempSettings;

public class MoveEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onMove(PlayerMoveEvent e) {
    HDPlayer HDPlayer = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    if (Game.getState() == GameState.IN_GAME) {
      try {
        int pos1 = Settings.pos1.getBlockY() - 1;
        int pos2 = HDPlayer.getLocation().getBlockY();
        if (pos2 < pos1)
          Game.eliminate(HDPlayer, HDPlayer.getLocation());
      } catch (Exception exception) {}
    } else if (Game.getState() == GameState.WAITING || 
      Game.getState() == GameState.STARTING) {
      if (!TempSettings.build)
        try {
          int y1 = Settings.lobby.getBlockY() - 10;
          int y2 = HDPlayer.getLocation().getBlockY();
          if (y2 < y1)
            HDPlayer.getPlayer().teleport(Settings.lobby);
        } catch (Exception exception) {} 
    } 
  }
}
