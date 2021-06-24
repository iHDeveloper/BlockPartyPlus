package me.iHDeveloper.api.commands;

import me.iHDeveloper.api.command.Command;
import me.iHDeveloper.api.command.CommandInfo;
import me.iHDeveloper.api.hologram.Hologram;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.command.CommandSender;

@CommandInfo(commands = {"hologram"}, args = {"create", "delete", "edit", "info"}, permissions = {}, isOp = true)
public class HologramCommand implements Command {
  private static HologramCommand instance;

  public static void destroyAllHolograms() {
    if (instance == null)
      return; 
    for (Hologram hologram : Manager.holograms.values()) {
      hologram.getTextList().clear();
      hologram.update();
      hologram.hideAll();
    } 
  }
  
  public static void updateHolograms() {
    if (instance == null)
      return; 
    for (Hologram hologram : Manager.holograms.values())
      hologram.update(); 
  }
  
  public HologramCommand() {
    Manager.loadAll();
    if (instance == null)
      instance = this; 
  }
  
  public void onConsole(CommandSender sender, String arg, String[] args) {}
  
  public void onPlayer(HDPlayer sender, String arg, String[] args) {
    if (arg.equalsIgnoreCase("create")) {
      if (args.length == 2) {
        String id = args[0];
        String distance = args[1];
        try {
          double d = Double.parseDouble(distance);
          Manager.create(id, sender.getLocation(), d);
          sender.send("&aDone! &eComplete create hologram with id `%s`", id);
          Manager.saveAll();
        } catch (IllegalArgumentException | NullPointerException ex) {
          sender.sendError(ex.getMessage());
          return;
        }
        return;
      } 
      sender.sendError("/holo create <id> <distance>");
      return;
    } 
    if (arg.equalsIgnoreCase("delete")) {
      if (args.length == 1) {
        String id = args[0];
        try {
          Manager.delete(id);
          sender.send("&aDone! &eComplete delete hologram `%s`", id);
          Manager.saveAll();
        } catch (IllegalArgumentException ex) {
          sender.sendError(ex.getMessage());
          return;
        } 
        return;
      } 
      sender.sendError("/holo delete <id>");
      return;
    } 
    if (arg.equalsIgnoreCase("edit")) {
      if (args.length >= 2) {
        String type = args[0];
        String id = args[1];
        if (type.equalsIgnoreCase("add")) {
          if (args.length >= 3) {
            StringBuilder displayNameBuilder = new StringBuilder(args[2]);
            for (int i = 3; i < args.length; i++)
              displayNameBuilder.append(" ").append(args[i]);
            String displayName = iHDeveloperAPI.color(displayNameBuilder.toString());
            try {
              Manager.addText(id, displayName);
              sender.send("&aDone! &eAdded `%s&e` to `%s`", displayName, id);
              Manager.saveAll();
            } catch (IllegalArgumentException ex) {
              sender.sendError(ex.getMessage());
              return;
            } 
            return;
          } 
          sender.sendError("/hologram edit add <id> [displayname]");
          return;
        } 
        if (type.equalsIgnoreCase("remove")) {
          if (args.length == 3) {
            try {
              int i = Integer.parseInt(args[2]);
              Manager.removeText(id, i);
              sender.send("&aDone! &eComplete remove line `%s` @ `%s`", i, id);
              Manager.saveAll();
            } catch (IllegalArgumentException ex) {
              sender.sendError(ex.getMessage());
              return;
            }
            return;
          } 
          sender.sendError("/hologram edit remove <id> <line>");
          return;
        } 
        if (type.equalsIgnoreCase("line")) {
          if (args.length >= 4) {
            String line = args[2];
            StringBuilder displayNameBuilder = new StringBuilder(args[3]);
            for (int i = 4; i < args.length; i++)
              displayNameBuilder.append(" ").append(args[i]);
            String displayName = iHDeveloperAPI.color(displayNameBuilder.toString());
            try {
              int l = Integer.parseInt(line);
              Manager.editText(id, l, displayName);
              sender.send("&aDone! &eComplete edit line `%s` with `%s` @ `%s`", l, displayName, id);
              Manager.saveAll();
            } catch (IllegalArgumentException ex) {
              sender.sendError(ex.getMessage());
              return;
            }
            return;
          } 
          sender.sendError("/hologram edit line <id> <line> [displayname]");
          return;
        } 
        if (type.equalsIgnoreCase("distance")) {
          if (args.length == 3) {
            String distance = args[2];
            try {
              double d = Double.parseDouble(distance);
              Manager.editDistance(id, d);
              sender.send("&aDone! &eComplete set distance `%s` @ `%s`", distance, id);
              Manager.saveAll();
            } catch (IllegalArgumentException ex) {
              sender.sendError(ex.getMessage());
              return;
            }
            return;
          } 
          sender.sendError("/hologram edit distance <id> <distance>");
          return;
        } 
      } 
      sender.sendError("/holo edit add");
      sender.sendError("/holo edit remove");
      sender.sendError("/holo edit line");
      sender.sendError("/holo edit distance");
      return;
    } 
    if (arg.equalsIgnoreCase("info") && 
      args.length == 1) {
      String id = args[0];
      try {
        sender.sendSub();
        Hologram hologram = Manager.getHologram(id);
        sender.send("&eId: &f%s", id);
        sender.send("&eDistance: &f%s", hologram.getDistance());
        sender.send("&eLines: ");
        for (int i = 0; i < hologram.getTextList().size(); i++) {
          sender.send("&b-|- &6%s: &f%s", i, hologram.getTextList().get(i));
        } 
        sender.sendSub();
        return;
      } catch (IllegalArgumentException ex) {
        sender.sendError(ex.getMessage());
        return;
      } 
    } 
    sender.sendSub();
    sender.send("&9/hologram &ecreate &7<id> <distance>");
    sender.send("&9/hologram &edelete &7<id> <line>");
    sender.send("&9/hologram &eedit &6add &7<id> [displayname]");
    sender.send("&9/hologram &eedit &6remove &7<id>");
    sender.send("&9/hologram &eedit &6line &7<id> <line> [displayname]");
    sender.send("&9/hologram &eedit &6distance &7<id> <distance>");
    sender.send("&9/hologram &einfo");
    sender.sendSub();
  }
}
