package sa.heroz.blockpartypls;

import me.iHDeveloper.api.command.CommandManager;
import me.iHDeveloper.api.commands.HologramCommand;
import me.iHDeveloper.api.exceptions.APIException;
import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
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

public class Main extends JavaPlugin {
  public void onEnable() {
    try {
      Console.log("Loading The Game [LOADING STATE]", new Object[0]);
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
        Console.log("&cThe game api doesn't loaded!", new Object[0]);
        ex.printStackTrace();
        setEnabled(false);
      } 
      Bukkit.getScheduler().runTask((Plugin)this, new Runnable() {
            public void run() {
              Console.log("&eLoading config...", new Object[0]);
              Settings.load();
              Console.log("&eLoading game...", new Object[0]);
              Game.host();
            }
          });
      registerEvents();
      registerCommands();
      Console.log("&aEnabled!", new Object[0]);
    } catch (Exception ex) {
      ex.printStackTrace();
      setEnabled(false);
      Console.log("&cDisabled! ||>> &cERR: &bAPI Catch!", new Object[0]);
    } 
  }
  
  private void registerEvents() {
    PluginManager pm = iHDeveloperAPI.getPluginManager();
    pm.registerEvents(new LoginEvent(), (Plugin)this);
    pm.registerEvents(new JoinEvent(), (Plugin)this);
    pm.registerEvents(new QuitEvent(), (Plugin)this);
    pm.registerEvents(new WeatherChangeEvent(), (Plugin)this);
    pm.registerEvents(new DamageEvent(), (Plugin)this);
    pm.registerEvents(new MoveEvent(), (Plugin)this);
    pm.registerEvents(new ChatEvent(), (Plugin)this);
    pm.registerEvents(new ArmorStandProtectEvent(), (Plugin)this);
    pm.registerEvents(new PingEvent(), (Plugin)this);
    pm.registerEvents(new GameModeChangeEvent(), (Plugin)this);
    pm.registerEvents(new BlocksEvent(), (Plugin)this);
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
    Console.log("&eDestorying all holograms...", new Object[0]);
    StatsHologram.destoryAllHolograms();
    HologramCommand.destoryAllHolograms();
    Console.log("&cDisabled!", new Object[0]);
  }
}
