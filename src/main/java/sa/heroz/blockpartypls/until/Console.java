package sa.heroz.blockpartypls.until;

import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class Console {
  private static String prefix = "&8|&7| &6Heroz&7/&eBlockParty+ &7|";
  
  public static void log(String message, Object... args) {
    send(message, args);
  }
  
  private static void send(String message, Object... args) {
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    console.sendMessage(iHDeveloperAPI.color(String.valueOf(prefix) + " " + message, args));
  }
}
