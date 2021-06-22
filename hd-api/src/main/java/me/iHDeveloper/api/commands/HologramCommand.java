package me.iHDeveloper.api.commands;

import me.iHDeveloper.api.command.Command;
import me.iHDeveloper.api.command.CommandInfo;
import me.iHDeveloper.api.hologram.Hologram;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.command.CommandSender;

@CommandInfo(commands = {"hologram"}, args = {"create", "delete", "edit", "info"}, permissions = {}, isOp = true)
public class HologramCommand implements Command {
  private static HologramCommand instance;
  
  public static HologramCommand getInstance() {
    return instance;
  }
  
  public static void destoryAllHolograms() {
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
  
  public void onPlayer(Player sender, String arg, String[] args) {
    if (arg.equalsIgnoreCase("create")) {
      if (args.length == 2) {
        String id = args[0];
        String distance = args[1];
        try {
          double d = Double.parseDouble(distance);
          Manager.create(id, sender.getLocation(), d);
          sender.send("&aDone! &eComplete create hologram with id `%s`", new Object[] { id });
          Manager.saveAll();
        } catch (NumberFormatException ex) {
          sender.sendError(ex.getMessage(), new Object[0]);
          return;
        } catch (IllegalArgumentException ex) {
          sender.sendError(ex.getMessage(), new Object[0]);
          return;
        } catch (NullPointerException ex) {
          sender.sendError(ex.getMessage(), new Object[0]);
          return;
        } 
        return;
      } 
      sender.sendError("/holo create <id> <distance>", new Object[0]);
      return;
    } 
    if (arg.equalsIgnoreCase("delete")) {
      if (args.length == 1) {
        String id = args[0];
        try {
          Manager.delete(id);
          sender.send("&aDone! &eComplete delete hologram `%s`", new Object[] { id });
          Manager.saveAll();
        } catch (IllegalArgumentException ex) {
          sender.sendError(ex.getMessage(), new Object[0]);
          return;
        } 
        return;
      } 
      sender.sendError("/holo delete <id>", new Object[0]);
      return;
    } 
    if (arg.equalsIgnoreCase("edit")) {
      if (args.length >= 2) {
        String type = args[0];
        String id = args[1];
        if (type.equalsIgnoreCase("add")) {
          if (args.length >= 3) {
            String displayname = args[2];
            for (int i = 3; i < args.length; i++)
              displayname = String.valueOf(displayname) + " " + args[i]; 
            displayname = iHDeveloperAPI.color(displayname, new Object[0]);
            try {
              Manager.addText(id, displayname);
              sender.send("&aDone! &eAdded `%s&e` to `%s`", new Object[] { displayname, id });
              Manager.saveAll();
            } catch (IllegalArgumentException ex) {
              sender.sendError(ex.getMessage(), new Object[0]);
              return;
            } 
            return;
          } 
          sender.sendError("/hologram edit add <id> [displayname]", new Object[0]);
          return;
        } 
        if (type.equalsIgnoreCase("remove")) {
          if (args.length == 3) {
            try {
              int i = Integer.parseInt(args[2]);
              Manager.removeText(id, i);
              sender.send("&aDone! &eComplete remove line `%s` @ `%s`", new Object[] { Integer.valueOf(i), id });
              Manager.saveAll();
            } catch (NumberFormatException ex) {
              sender.sendError(ex.getMessage(), new Object[0]);
              return;
            } catch (IllegalArgumentException ex) {
              sender.sendError(ex.getMessage(), new Object[0]);
              return;
            } 
            return;
          } 
          sender.sendError("/hologram edit remove <id> <line>", new Object[0]);
          return;
        } 
        if (type.equalsIgnoreCase("line")) {
          if (args.length >= 4) {
            String line = args[2];
            String displayname = args[3];
            for (int i = 4; i < args.length; i++)
              displayname = String.valueOf(displayname) + " " + args[i]; 
            displayname = iHDeveloperAPI.color(displayname, new Object[0]);
            try {
              int l = Integer.parseInt(line);
              Manager.editText(id, l, displayname);
              sender.send("&aDone! &eComplete edit line `%s` with `%s` @ `%s`", new Object[] { Integer.valueOf(l), displayname, id });
              Manager.saveAll();
            } catch (NumberFormatException ex) {
              sender.sendError(ex.getMessage(), new Object[0]);
              return;
            } catch (IllegalArgumentException ex) {
              sender.sendError(ex.getMessage(), new Object[0]);
              return;
            } 
            return;
          } 
          sender.sendError("/hologram edit line <id> <line> [displayname]", new Object[0]);
          return;
        } 
        if (type.equalsIgnoreCase("distance")) {
          if (args.length == 3) {
            String distance = args[2];
            try {
              double d = Double.parseDouble(distance);
              Manager.editDistance(id, d);
              sender.send("&aDone! &eComplete set distance `%s` @ `%s`", new Object[] { distance, id });
              Manager.saveAll();
            } catch (NumberFormatException ex) {
              sender.sendError(ex.getMessage(), new Object[0]);
              return;
            } catch (IllegalArgumentException ex) {
              sender.sendError(ex.getMessage(), new Object[0]);
              return;
            } 
            return;
          } 
          sender.sendError("/hologram edit distance <id> <distance>", new Object[0]);
          return;
        } 
      } 
      sender.sendError("/holo edit add", new Object[0]);
      sender.sendError("/holo edit remove", new Object[0]);
      sender.sendError("/holo edit line", new Object[0]);
      sender.sendError("/holo edit distance", new Object[0]);
      return;
    } 
    if (arg.equalsIgnoreCase("info") && 
      args.length == 1) {
      String id = args[0];
      try {
        sender.sendSub();
        Hologram hologram = Manager.getHologram(id);
        sender.send("&eId: &f%s", new Object[] { id });
        sender.send("&eDistance: &f%s", new Object[] { Double.valueOf(hologram.getDistance()) });
        sender.send("&eLines: ", new Object[0]);
        for (int i = 0; i < hologram.getTextList().size(); i++) {
          sender.send("&b-|- &6%s: &f%s", new Object[] { Integer.valueOf(i), hologram.getTextList().get(i) });
        } 
        sender.sendSub();
        return;
      } catch (IllegalArgumentException ex) {
        sender.sendError(ex.getMessage(), new Object[0]);
        return;
      } 
    } 
    sender.sendSub();
    sender.send("&9/hologram &ecreate &7<id> <distance>", new Object[0]);
    sender.send("&9/hologram &edelete &7<id> <line>", new Object[0]);
    sender.send("&9/hologram &eedit &6add &7<id> [displayname]", new Object[0]);
    sender.send("&9/hologram &eedit &6remove &7<id>", new Object[0]);
    sender.send("&9/hologram &eedit &6line &7<id> <line> [displayname]", new Object[0]);
    sender.send("&9/hologram &eedit &6distance &7<id> <distance>", new Object[0]);
    sender.send("&9/hologram &einfo", new Object[0]);
    sender.sendSub();
  }
}
