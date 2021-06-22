package me.iHDeveloper.api.command;

import me.iHDeveloper.api.player.Player;
import org.bukkit.command.CommandSender;

public interface Command {
  void onConsole(CommandSender paramCommandSender, String paramString, String[] paramArrayOfString);
  
  void onPlayer(Player paramPlayer, String paramString, String[] paramArrayOfString);
}
