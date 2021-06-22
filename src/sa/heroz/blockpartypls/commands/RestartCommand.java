package sa.heroz.blockpartypls.commands;

import me.iHDeveloper.api.command.Command;
import me.iHDeveloper.api.command.CommandInfo;
import me.iHDeveloper.api.player.Player;
import me.iHDeveloper.api.thread.GameThreadManager;
import org.bukkit.command.CommandSender;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.game.GameState;
import sa.heroz.blockpartypls.threads.RestartThread;

@CommandInfo(commands = {"herozrestart"}, args = {""}, permissions = {""}, isOp = true, player = false)
public class RestartCommand implements Command {
  private final GameThreadManager thread = new GameThreadManager(new RestartThread());
  
  public void onConsole(CommandSender sender, String arg, String[] args) {}
  
  public void onPlayer(Player sender, String arg, String[] args) {
    Game.setState(GameState.RESTARTING);
    Game.updateAll();
    this.thread.run();
    sender.send("&eIn 15sec the server will be restarting", new Object[0]);
  }
}
