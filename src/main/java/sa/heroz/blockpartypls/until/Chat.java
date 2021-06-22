package sa.heroz.blockpartypls.until;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.Bukkit;

public class Chat {
  private static String prefix = "&8|&7| &6Heroz &7|";
  
  public static void broadcast(String message, Object... args) {
    Bukkit.broadcastMessage(iHDeveloperAPI.color(message, args));
  }
  
  public static void send(HDPlayer HDPlayer, String message, Object... args) {
    HDPlayer.send(iHDeveloperAPI.color(String.valueOf(prefix) + " " + message, args), new Object[0]);
  }
}
