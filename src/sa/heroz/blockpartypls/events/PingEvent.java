package sa.heroz.blockpartypls.events;

import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.game.GameState;
import sa.heroz.blockpartypls.until.Settings;

public class PingEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPing(ServerListPingEvent e) {
    e.setMaxPlayers(Settings.maxPlayers);
    String state = "";
    switch (Game.getState()) {
      case STARTING:
        state = "&a&lOPEN";
        break;
      case WAITING:
        state = "&a&lOPEN";
        break;
      case IN_GAME:
        state = "&c&lIN-GAME";
        break;
      case null:
        state = "&e&lENDED";
        break;
      default:
        state = "&4&lOFFLINE";
        break;
    } 
    e.setMotd(iHDeveloperAPI.color("%s&8&l || %s\n&b&l-|-  &e&l&nJOIN TO PLAY!&r &8&l|| &e&lBy: &f&liHDeveloper", new Object[] { "              &6&lBLOCKPARTY+ &8&l|| &7V0.2", 
            state }));
  }
}
