package sa.heroz.blockpartypls.commands;

import me.iHDeveloper.api.command.Command;
import me.iHDeveloper.api.command.CommandInfo;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.command.CommandSender;
import sa.heroz.blockpartypls.until.TempSettings;

@CommandInfo(commands = {"build", "b"}, args = {""}, permissions = {""}, isOp = true, player = true)
public class BuildCommand implements Command {
  public void onConsole(CommandSender sender, String arg, String[] args) {}
  
  public void onPlayer(HDPlayer sender, String arg, String[] args) {
    if (args.length == 0) {
      if (TempSettings.build) {
        TempSettings.build = false;
      } else if (!TempSettings.build) {
        TempSettings.build = true;
      } 
      sender.send("&eBuild Mode is %s", new Object[] { TempSettings.build ? "&aenable" : "&cdisable" });
    } else if (args.length == 1 && 
      args[0].equalsIgnoreCase("-info")) {
      sender.send("&eBuild Mode is %s", new Object[] { TempSettings.build ? "&aenable" : "&cdisable" });
    } 
  }
}
