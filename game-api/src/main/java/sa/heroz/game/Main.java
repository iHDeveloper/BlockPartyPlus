package sa.heroz.game;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import sa.heroz.game.api.GameAPI;
import sa.heroz.game.events.JoinEvent;
import sa.heroz.game.until.Console;

public class Main extends JavaPlugin {
  private static GameAPI api;
  
  public static GameAPI getGameAPI() {
    return api;
  }
  
  public void onEnable() {
    try {
      String a = Bukkit.getBukkitVersion();
      String b = a.substring(0, 3);
      b = b.replace(".", "_");
      try {
        Class<?> c = Class.forName("sa.heroz.game.api.v" + b + ".HerozGameAPI");
        Object obj = c.newInstance();
        api = (GameAPI)obj;
        Class<?> h = HerozGame.class;
        h.newInstance();
      } catch (Exception ex) {
        ex.printStackTrace();
        Console.log("&cCannot load the api class! &7EX: %s", new Object[] { ex.getMessage() });
        setEnabled(false);
      } 
      registerEvents();
    } catch (Exception ex) {
      setEnabled(false);
    } 
    Console.log("&aEnabled!");
  }
  
  private void registerEvents() {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new JoinEvent(), this);
  }
  
  public void onDisable() {
    HerozGame.stop();
    Console.log("&cDisabled!");
  }
}
