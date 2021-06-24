package sa.heroz.blockpartypls;

import me.iHDeveloper.api.command.CommandManager;
import me.iHDeveloper.api.commands.HologramCommand;
import me.iHDeveloper.api.exceptions.APIException;
import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import sa.heroz.blockpartypls.commands.BuildCommand;
import sa.heroz.blockpartypls.commands.GameCommand;
import sa.heroz.blockpartypls.commands.RestartCommand;
import sa.heroz.blockpartypls.commands.StatsCommand;
import sa.heroz.blockpartypls.events.ArmorStandProtectEvent;
import sa.heroz.blockpartypls.events.BlocksEvent;
import sa.heroz.blockpartypls.events.ChatEvent;
import sa.heroz.blockpartypls.events.DamageEvent;
import sa.heroz.blockpartypls.events.GameModeChangeEvent;
import sa.heroz.blockpartypls.events.JoinEvent;
import sa.heroz.blockpartypls.events.LoginEvent;
import sa.heroz.blockpartypls.events.MoveEvent;
import sa.heroz.blockpartypls.events.PingEvent;
import sa.heroz.blockpartypls.events.QuitEvent;
import sa.heroz.blockpartypls.events.WeatherChangeEvent;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.holograms.StatsHologram;
import sa.heroz.blockpartypls.until.Console;
import sa.heroz.blockpartypls.until.Settings;
import sa.heroz.game.HerozGame;

@SuppressWarnings("deprecation")
public class Main extends JavaPlugin {
  public void onEnable() {
    try {
      Console.log("Loading The Game [LOADING STATE]");
      iHDeveloperAPI.setPlugin(this);
      try {
        if (!HerozGame.isStarted()) {
          HerozGame.setName("BlockParty+");
          HerozGame.setAuthor("iHDeveloper");
          HerozGame.setVersion("V0.2");
          HerozGame.setIsPrototype(true);
          HerozGame.setBossBar(true);
          HerozGame.setTitle(true);
        } 
      } catch (Exception ex) {
        Console.log("&cThe game api doesn't loaded!");
        ex.printStackTrace();
        setEnabled(false);
      } 
      Bukkit.getScheduler().runTask(this, () -> {
        Console.log("&eLoading config...");
        Settings.load();
        Console.log("&eLoading game...");
        Game.host();
      });
      registerEvents();
      registerCommands();
      Console.log("&aEnabled!");
    } catch (Exception ex) {
      ex.printStackTrace();
      setEnabled(false);
      Console.log("&cDisabled! ||>> &cERR: &bAPI Catch!");
    } 
  }
  
  private void registerEvents() {
    PluginManager pm = iHDeveloperAPI.getPluginManager();
    pm.registerEvents(new LoginEvent(), this);
    pm.registerEvents(new JoinEvent(), this);
    pm.registerEvents(new QuitEvent(), this);
    pm.registerEvents(new WeatherChangeEvent(), this);
    pm.registerEvents(new DamageEvent(), this);
    pm.registerEvents(new MoveEvent(), this);
    pm.registerEvents(new ChatEvent(), this);
    pm.registerEvents(new ArmorStandProtectEvent(), this);
    pm.registerEvents(new PingEvent(), this);
    pm.registerEvents(new GameModeChangeEvent(), this);
    pm.registerEvents(new BlocksEvent(), this);
  }
  
  private void registerCommands() {
    try {
      CommandManager.addCommand(new GameCommand());
      CommandManager.addCommand(new BuildCommand());
      CommandManager.addCommand(new RestartCommand());
      CommandManager.addCommand(new StatsCommand());
    } catch (APIException e) {
      e.printStackTrace();
    } 
  }
  
  public void onDisable() {
    Settings.save();
    Console.log("&eDestroying all holograms...");
    StatsHologram.destoryAllHolograms();
    HologramCommand.destroyAllHolograms();
    Console.log("&cDisabled!");
  }
}
