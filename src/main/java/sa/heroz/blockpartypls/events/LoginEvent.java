package sa.heroz.blockpartypls.events;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.game.GameState;
import sa.heroz.blockpartypls.until.Console;
import sa.heroz.blockpartypls.until.Settings;

public class LoginEvent implements Listener {
  @EventHandler(priority = EventPriority.LOWEST)
  public void onLogin(PlayerLoginEvent e) {
    Player p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    Console.log("%s trying to login", new Object[] { e.getPlayer().getName() });
    if (Game.getState() == GameState.WAITING) {
      if (Game.getAllPlayers().size() >= Settings.maxPlayers) {
        Console.log("%s unable to login [ Game is Full! ] ", new Object[] { p.getName() });
        e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, iHDeveloperAPI.color(Settings.full, new Object[0]));
        return;
      } 
    } else if (Game.getState().equals(GameState.RESTARTING)) {
      Console.log("%s unable to login [ The server is restarting ] ", new Object[] { p.getName() });
      e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, iHDeveloperAPI.color("&eThe server is restarting...", new Object[0]));
    } else if (Game.getState().equals(GameState.IN_GAME)) {
      Console.log("%s unable to login [ Game already started! ] ", new Object[] { p.getName() });
      e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, iHDeveloperAPI.color(Settings.already, new Object[0]));
    } else {
      Console.log("%s unable to login [ The server is offline ] ", new Object[] { p.getName() });
      e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, iHDeveloperAPI.color("&cThe server is offline...", new Object[0]));
      return;
    } 
    e.getPlayer().sendMessage(iHDeveloperAPI.color("&9", new Object[0]));
    e.allow();
  }
}
