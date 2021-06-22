package sa.heroz.blockpartypls.commands;

import me.iHDeveloper.api.command.Command;
import me.iHDeveloper.api.command.CommandInfo;
import me.iHDeveloper.api.player.Player;
import me.iHDeveloper.api.spectate.SpectateSystem;
import net.minecraft.server.v1_8_R3.Vector3f;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sa.heroz.blockpartypls.entities.RIPEntity;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.until.Floor;
import sa.heroz.blockpartypls.until.Settings;
import sa.heroz.blockpartypls.until.floor.BlockChange;

@CommandInfo(commands = {"game"}, args = {"config", "start", "test", "debug"}, isOp = true, player = true, console = false, permissions = {""})
public class GameCommand implements Command {
  public void onConsole(CommandSender sender, String arg, String[] args) {}
  
  private Location pos1 = null;
  
  private Location pos2 = null;
  
  public void onPlayer(Player sender, String arg, String[] args) {
    if (arg.equalsIgnoreCase("config")) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("lobby")) {
          Settings.setLobby(sender.getLocation());
        } else if (args[0].equalsIgnoreCase("game")) {
          Settings.setGame(sender.getLocation());
        } else if (args[0].equalsIgnoreCase("loading")) {
          Settings.setLoading(sender.getLocation());
        } else if (args[0].equalsIgnoreCase("stats")) {
          Settings.setHologramStats(sender.getLocation());
        } else if (args[0].equalsIgnoreCase("kills")) {
          Settings.setHologramTopKills(sender.getLocation());
        } else if (args[0].equalsIgnoreCase("points")) {
          Settings.setHologramTopPoints(sender.getLocation());
        } else {
          if (args[0].equalsIgnoreCase("pos1")) {
            this.pos1 = sender.getLocation();
            sender.send("&aDone. Please set posittion 2 to complete!", new Object[0]);
            pointBlock(sender, 1);
            return;
          } 
          if (args[0].equalsIgnoreCase("pos2")) {
            if (this.pos1 == null) {
              sender.sendError("Please set position 1 before 2", new Object[0]);
              return;
            } 
            this.pos2 = sender.getLocation();
            pointBlock(sender, 2);
            Settings.setPosition1(this.pos1);
            Settings.setPosition2(this.pos2);
            clearPoints();
            sender.send("&aDone! &eyou can play now BLOCKPARYT+ YAY! ;D", new Object[0]);
            return;
          } 
          if (args[0].equalsIgnoreCase("author")) {
            Location location = sender.getLocation();
            location.setX(sender.getLocation().getBlockX());
            Settings.setAuthorNPC(location);
          } else {
            sender.send("&9/game &econfig &7lobby", new Object[0]);
            sender.send("&9/game &econfig &7loading", new Object[0]);
            sender.send("&9/game &econfig &7author", new Object[0]);
            sender.send("&9/game &econfig &7game", new Object[0]);
            sender.send("&9/game &econfig &7pos1", new Object[0]);
            sender.send("&9/game &econfig &7pos2", new Object[0]);
            return;
          } 
        } 
        sender.send("&aDone!", new Object[0]);
        return;
      } 
      sender.send("&9/game &econfig &7lobby", new Object[0]);
      sender.send("&9/game &econfig &7game", new Object[0]);
      sender.send("&9/game &econfig &7pos1", new Object[0]);
      sender.send("&9/game &econfig &7pos2", new Object[0]);
      return;
    } 
    if (arg.equalsIgnoreCase("start")) {
      sender.send("&aThe game is starting", new Object[0]);
      Game.startByAdmin(sender);
      return;
    } 
    if (arg.equalsIgnoreCase("test")) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("1")) {
          test1();
          sender.send("&eTest...", new Object[0]);
          return;
        } 
        if (args[0].equalsIgnoreCase("2")) {
          test2();
          return;
        } 
        if (args[0].equalsIgnoreCase("5")) {
          test5();
          return;
        } 
        if (args[0].equalsIgnoreCase("rip1")) {
          createRIP(sender);
          return;
        } 
        if (args[0].equalsIgnoreCase("ripinfo")) {
          ripInfo(sender);
          return;
        } 
        if (args[0].equalsIgnoreCase("rip2")) {
          killRIP();
          return;
        } 
        if (args[0].equalsIgnoreCase("rip3")) {
          showRIP();
          return;
        } 
        if (args[0].equalsIgnoreCase("spectate")) {
          SpectateSystem.addPlayer(sender);
          return;
        } 
        if (args[0].equalsIgnoreCase("player")) {
          SpectateSystem.removePlayer(sender);
          return;
        } 
        if (args[0].equalsIgnoreCase("4")) {
          Player p = sender.getPlayer();
          Location loc = p.getLocation();
          Location target = new Location(loc.getWorld(), loc.getBlockX(), (loc.getBlockY() - 1), loc.getBlockZ());
          Block block = p.getWorld().getBlockAt(target);
          block.setType(Material.WOOL);
          block.setData(DyeColor.RED.getData());
          a(DyeColor.BLACK);
          a(DyeColor.BLUE);
          a(DyeColor.BROWN);
          a(DyeColor.CYAN);
          a(DyeColor.GRAY);
          a(DyeColor.GREEN);
          a(DyeColor.LIGHT_BLUE);
          a(DyeColor.LIME);
          a(DyeColor.MAGENTA);
          a(DyeColor.ORANGE);
          a(DyeColor.PINK);
          a(DyeColor.PURPLE);
          a(DyeColor.RED);
          a(DyeColor.SILVER);
          a(DyeColor.WHITE);
          a(DyeColor.YELLOW);
        } else if (args[0].equalsIgnoreCase("3")) {
          return;
        } 
      } 
      sender.send("&9/game &etest 1", new Object[0]);
      sender.send("&9/game &etest 2", new Object[0]);
      return;
    } 
    if (arg.equalsIgnoreCase("debug")) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("players")) {
          sender.sendSub();
          sender.send("&9All: &e%s", new Object[] { Integer.valueOf(Game.getAllPlayers().size()) });
          sender.send("&9Alive: &e%s", new Object[] { Integer.valueOf(Game.getAlivePlayers().size()) });
          sender.send("&9Spectators: &e%s", new Object[] { Integer.valueOf(Game.getSpectatorsPlayers().size()) });
          sender.sendSub();
        } 
      } else {
        sender.send("&9/game &edebug players", new Object[0]);
      } 
      return;
    } 
    sender.send("&9/game &econfig", new Object[0]);
    sender.send("&9/game &estart", new Object[0]);
  }
  
  private void showRIP() {
    if (this.ripEntity == null)
      return; 
    this.ripEntity.showAll();
  }
  
  private void ripInfo(Player sender) {
    if (this.ripEntity == null)
      return; 
    sender.send("&fHead Pose: %s", new Object[] { toVectorString((this.ripEntity.getEntity()).headPose) });
    sender.send("&fBody Pose: %s", new Object[] { toVectorString((this.ripEntity.getEntity()).bodyPose) });
    sender.send("&fLeftArm Pose: %s", new Object[] { toVectorString((this.ripEntity.getEntity()).leftArmPose) });
    sender.send("&fRightArm Pose: %s", new Object[] { toVectorString((this.ripEntity.getEntity()).rightArmPose) });
    sender.send("&fLeftLeg Pose: %s", new Object[] { toVectorString((this.ripEntity.getEntity()).leftLegPose) });
    sender.send("&fRightLeg Pose: %s", new Object[] { toVectorString((this.ripEntity.getEntity()).rightLegPose) });
  }
  
  private String toVectorString(Vector3f v) {
    String x = "";
    x = String.format("X:%s | Y:%s | Z:%s", new Object[] { Float.valueOf(v.getX()), Float.valueOf(v.getY()), Float.valueOf(v.getZ()) });
    return x;
  }
  
  private RIPEntity ripEntity = null;
  
  private void createRIP(Player sender) {
    if (this.ripEntity != null)
      return; 
    this.ripEntity = new RIPEntity(sender, sender.getLocation());
    System.out.println("RIPEntity: " + this.ripEntity);
  }
  
  private void killRIP() {
    if (this.ripEntity == null)
      return; 
    this.ripEntity.hideAll();
    this.ripEntity.die();
    this.ripEntity = null;
  }
  
  private void a(DyeColor color) {
    System.out.println(String.valueOf(color.toString()) + " = " + color.getData());
  }
  
  private Block point1 = null;
  
  private Block point2 = null;
  
  private Material t1 = null;
  
  private Material t2 = null;
  
  private void pointBlock(Player player, int i) {
    Location p = player.getLocation();
    World w = player.getLocation().getWorld();
    p.setY(p.getY() - 1.0D);
    p.setYaw(0.0F);
    p.setPitch(0.0F);
    Block b = w.getBlockAt(p);
    if (i == 1) {
      this.point1 = b;
      this.t1 = b.getType();
    } else if (i == 2) {
      this.point2 = b;
      this.t2 = b.getType();
    } 
    b.setType(Material.GOLD_BLOCK);
  }
  
  private void clearPoints() {
    this.point1.setType(this.t1);
    this.point2.setType(this.t2);
    this.point1 = null;
    this.point2 = null;
  }
  
  private void test1() {
    (new Floor(null)).drawFloor(new BlockChange() {
          public void onBlock(Block block) {
            if (block.getType().equals(Material.WOOL))
              block.setType(Material.GOLD_BLOCK); 
          }
        });
  }
  
  private void test2() {
    (new Floor(null)).drawFloor(new BlockChange() {
          public void onBlock(Block block) {
            if (block.getType().equals(Material.GOLD_BLOCK))
              block.setType(Material.WOOL); 
          }
        });
  }
  
  private void test5() {
    (Game.getGameThread()).round = 18;
  }
}
