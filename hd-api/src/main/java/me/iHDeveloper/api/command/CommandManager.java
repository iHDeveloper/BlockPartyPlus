package me.iHDeveloper.api.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.exceptions.APIException;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {
  private static CommandManager instance = new CommandManager();
  private static final Map<String, Command> commands = new HashMap<>();
  
  public static void load() {
    instance = new CommandManager();
  }
  
  public static void addCommand(Command command) throws APIException {
    if (instance == null)
      load(); 
    CommandInfo info = command.getClass().getAnnotation(CommandInfo.class);
    if (info == null)
      throw new APIException("Not found the command class information!"); 
    String[] commands = info.commands();
    byte b;
    int i;
    String[] arrayOfString1;
    for (i = (arrayOfString1 = commands).length, b = 0; b < i; ) {
      String cmd = arrayOfString1[b];
      try {
        iHDeveloperAPI.getCommand(cmd).setExecutor(instance);
      } catch (NullPointerException ex) {
        Debug.error("Cannot found command: %s", cmd);
      } 
      CommandManager.commands.put(cmd, command);
      b++;
    } 
  }
  
  public boolean onCommand(CommandSender sender, org.bukkit.command.Command bukkitCommand, String arg2, String[] args) {
    for (String name : commands.keySet()) {
      if (bukkitCommand.getName().equalsIgnoreCase(name)) {
        Command cmd = commands.get(name);
        CommandInfo info = cmd.getClass().getAnnotation(CommandInfo.class);
        String[] commandArgs = info.args();
        String arg = "";
        List<String> argsList = new LinkedList<>(Arrays.asList(args));
        if (args.length > 0) {
          byte b;
          int i;
          String[] arrayOfString;
          for (i = (arrayOfString = commandArgs).length, b = 0; b < i; ) {
            String a = arrayOfString[b];
            String str1 = args[0];
            if (str1.equalsIgnoreCase(a)) {
              arg = args[0];
              argsList.remove(0);
              break;
            } 
            b++;
          } 
        } 
        String[] cmdArgs = argsList.toArray(new String[0]);
        boolean isPlayer = !(sender instanceof org.bukkit.command.ConsoleCommandSender);
        if (info.player() && 
          !isPlayer) {
          sender.sendMessage(iHDeveloperAPI.color("&cThis command is only for player!"));
          return true;
        } 
        if (!isPlayer) {
          cmd.onConsole(sender, arg, cmdArgs);
        } else {
          if (info.isOp() && 
            !sender.isOp()) {
            sender.sendMessage(iHDeveloperAPI.color("&cYou must to be op for do this command!"));
            return true;
          } 
          if ((info.permissions()).length > 0) {
            boolean have = false;
            byte b;
            int i;
            String[] arrayOfString;
            for (i = (arrayOfString = info.permissions()).length, b = 0; b < i; ) {
              String permission = arrayOfString[b];
              if (sender.hasPermission(permission)) {
                have = true;
                break;
              } 
              b++;
            } 
            if (!have) {
              sender.sendMessage(iHDeveloperAPI.color("&cYou don't have any permission to do this command!s"));
              return true;
            } 
          } 
          HDPlayer HDPlayer = iHDeveloperAPI.getPlayer(((HDPlayer)sender).getName());
          cmd.onPlayer(
                  HDPlayer,
              arg, cmdArgs);
          Debug.log(sender.getName() + " process command /" + bukkitCommand.getName() + " " + arg);
        } 
        return true;
      } 
    } 
    Debug.log("Not found command /" + bukkitCommand.getName());
    return true;
  }
}
