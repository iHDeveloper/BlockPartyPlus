package me.iHDeveloper.api;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class Debug {
  private static String prefix = "&6[&3iHDeveloper&cAPI&6]&7";
  
  public static void log(String message, Object... args) {
    if (!iHDeveloperAPI.getConfig().getBoolean("debug"))
      return; 
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    console.sendMessage(iHDeveloperAPI.color("%s %s", new Object[] { prefix, String.format(message, args) }));
  }
  
  public static void error(String message, Object... args) {
    log("&c%s", new Object[] { String.format(message, args) });
  }
}
