package me.iHDeveloper.api.commands;

import me.iHDeveloper.api.command.Command;
import me.iHDeveloper.api.command.CommandInfo;
import me.iHDeveloper.api.issue.Issue;
import me.iHDeveloper.api.player.Player;
import org.bukkit.command.CommandSender;

@CommandInfo(commands = {"issue"}, args = {}, permissions = {}, isOp = true, player = true, console = false)
public class IssueCommand implements Command {
  public void onConsole(CommandSender sender, String arg, String[] args) {}
  
  public void onPlayer(Player sender, String arg, String[] args) {
    if (arg.equalsIgnoreCase("") && args.length == 1) {
      sender.send("&aHosting issue &e" + args[0], new Object[0]);
      Issue.log(sender, args[0]);
    } else {
      sender.sendError("/issue <id>", new Object[0]);
    } 
  }
}
