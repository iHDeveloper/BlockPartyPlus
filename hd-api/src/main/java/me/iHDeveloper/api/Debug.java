package me.iHDeveloper.api;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class Debug {
  private static final String PREFIX = "&6[&3iHDeveloper&cAPI&6]&7";

  public static void warn(String message, Object...args) {
    log("&e%s", String.format(message, args));
  }

  public static void error(String message, Object... args) {
    log("&c%s", String.format(message, args));
  }

  public static void log(String message, Object... args) {
    if (!iHDeveloperAPI.getConfig().getBoolean("debug"))
      return;
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    console.sendMessage(iHDeveloperAPI.color("%s %s", PREFIX, String.format(message, args)));
  }

}
