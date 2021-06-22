package sa.heroz.blockpartypls.until;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import sa.heroz.blockpartypls.game.Game;

public class Title {
  public static void clearAll() {
    broadcast(iHDeveloperAPI.color("&9", new Object[0]), "", 1, 5, 1);
  }
  
  public static void broadcast(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
    for (GamePlayer player : Game.getAlivePlayers()) {
      Player p = player.getPlayer();
      p.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    } 
  }
}
