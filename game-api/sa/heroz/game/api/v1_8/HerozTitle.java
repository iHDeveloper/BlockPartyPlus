package sa.heroz.game.api.v1_8;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sa.heroz.game.HerozGame;

public class HerozTitle {
  public static void show(Player p) {
    if (!HerozGame.canUseTitle())
      return; 
    int fadeIn = 20;
    int stay = 80;
    int fadeOut = 20;
    String title = iHDeveloperAPI.color("%s&e%s %s", new Object[] { HerozGame.isPrototype() ? "&7[PTL] " : "", 
          HerozGame.getName(), 
          "&7[" + HerozGame.getVersion() + "&7]" });
    String subtitle = iHDeveloperAPI.color("&eBy &8» &f%s", new Object[] { HerozGame.getAuthor() });
    p.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
  }
  
  public static void clear(Player p) {
    if (!HerozGame.canUseTitle())
      return; 
    p.sendTitle(iHDeveloperAPI.color("&9", new Object[0]), "", 1, 5, 1);
  }
  
  public static void clearAll() {
    if (!HerozGame.canUseTitle())
      return; 
    for (Player player : Bukkit.getOnlinePlayers())
      clear(iHDeveloperAPI.getPlayer(player.getName())); 
  }
}
