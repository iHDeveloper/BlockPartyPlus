package sa.heroz.blockpartypls.commands;

import me.iHDeveloper.api.command.Command;
import me.iHDeveloper.api.command.CommandInfo;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.tasks.GameTaskRegistry;
import org.bukkit.command.CommandSender;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.game.GameState;
import sa.heroz.blockpartypls.tasks.RestartTask;

@CommandInfo(commands = {"herozrestart"}, args = {""}, permissions = {""}, isOp = true, player = false)
public class RestartCommand implements Command {
  public void onConsole(CommandSender sender, String arg, String[] args) {}
  
  public void onPlayer(HDPlayer sender, String arg, String[] args) {
    Game.setState(GameState.RESTARTING);
    Game.updateAll();
    GameTaskRegistry.start(RestartTask.class);
    sender.send("&eIn 15sec the server will be restarting");
  }
}
