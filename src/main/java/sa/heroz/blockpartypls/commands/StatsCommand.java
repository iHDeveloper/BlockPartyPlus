package sa.heroz.blockpartypls.commands;

import me.iHDeveloper.api.command.Command;
import me.iHDeveloper.api.command.CommandInfo;
import me.iHDeveloper.api.player.Player;
import org.bukkit.command.CommandSender;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.until.GamePlayer;

@CommandInfo(commands = {"stats", "s", "records", "mystats", "profile"}, args = {}, permissions = {}, isOp = false, player = true)
public class StatsCommand implements Command {
  public void onConsole(CommandSender sender, String arg, String[] args) {}
  
  public void onPlayer(Player sender, String arg, String[] args) {
    if (args.length == 0) {
      GamePlayer stats = Game.getPlayer(sender.getUUID());
      sender.sendSub();
      sender.send("&eIn-Game:", new Object[0]);
      sender.send("&b-|- &eName: &f%s", new Object[] { sender.getName() });
      sender.send("&b-|- &eRole: &f%s", new Object[] { Game.getPlayerRole(stats) });
      sender.send("&b-|- &ePoints: &f%s", new Object[] { Integer.valueOf(stats.getPoints()) });
      sender.send("&b-|- &eKills: &f%s", new Object[] { Integer.valueOf(stats.getKills()) });
      sender.sendSub();
    } else {
      sender.send("&9/stats &e<player> &c&lNOT RELEASED!", new Object[0]);
    } 
  }
}
