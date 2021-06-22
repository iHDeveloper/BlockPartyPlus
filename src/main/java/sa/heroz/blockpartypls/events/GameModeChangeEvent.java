package sa.heroz.blockpartypls.events;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.spectate.SpectateSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.game.GamePlayerRole;
import sa.heroz.blockpartypls.until.GamePlayer;

public class GameModeChangeEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onGameModeChangeEvent(PlayerGameModeChangeEvent e) {
    try {
      HDPlayer HDPlayer = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
      GamePlayer p = Game.getPlayer(HDPlayer.getUUID());
      if (p.getRole().equals(GamePlayerRole.DEAD) && !SpectateSystem.is(HDPlayer)) {
        SpectateSystem.addPlayer(HDPlayer);
        e.setCancelled(true);
        return;
      } 
      if (p.getRole().equals(GamePlayerRole.LOSER) && p.getRole().equals(GamePlayerRole.WINNER))
        SpectateSystem.removePlayer(HDPlayer);
      e.setCancelled(false);
    } catch (NullPointerException nullPointerException) {}
  }
}
