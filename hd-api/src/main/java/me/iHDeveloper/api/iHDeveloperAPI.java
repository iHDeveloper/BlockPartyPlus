package me.iHDeveloper.api;

import me.iHDeveloper.api.command.CommandManager;
import me.iHDeveloper.api.commands.HologramCommand;
import me.iHDeveloper.api.commands.IssueCommand;
import me.iHDeveloper.api.events.BlockEvents;
import me.iHDeveloper.api.events.CreatureEvent;
import me.iHDeveloper.api.events.DamageEvent;
import me.iHDeveloper.api.events.DeathEvent;
import me.iHDeveloper.api.events.FoodChangeEvent;
import me.iHDeveloper.api.events.InteractEvent;
import me.iHDeveloper.api.events.InventoryEvents;
import me.iHDeveloper.api.events.JoinEvent;
import me.iHDeveloper.api.events.KickEvent;
import me.iHDeveloper.api.events.LoginEvent;
import me.iHDeveloper.api.events.PingEvent;
import me.iHDeveloper.api.events.PlayerWorldChangeEvent;
import me.iHDeveloper.api.events.QuitEvent;
import me.iHDeveloper.api.exceptions.APIException;
import me.iHDeveloper.api.permission.PermissionsManager;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.player.UUIDDatabase;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class iHDeveloperAPI {
  private static JavaPlugin plugin;
  
  private static LoginEvent loginEvent;
  
  private static JoinEvent joinEvent;
  
  private static InteractEvent interactEvent;
  
  private static KickEvent kickEvent;
  
  private static QuitEvent quitEvent;
  
  private static PingEvent pingEvent;
  
  private static CreatureEvent creatureEvent;
  
  private static InventoryEvents inventoryEvents;
  
  private static DeathEvent deathEvent;
  
  private static DamageEvent damageEvent;
  
  private static FoodChangeEvent foodChangeEvent;
  
  private static BlockEvents blockEvents;
  
  private static PlayerWorldChangeEvent worldChangeEvent;
  
  private static UUIDDatabase uuids;
  
  private static Map<UUID, HDPlayer> players;
  
  private static String prefix = "&b| &3iHDeveloper&cAPI&6 &b|";
  
  public static void setPlugin(JavaPlugin plugin) throws APIException {
    if (plugin == null)
      throw new APIException("No plugin support yet!"); 
    if (!plugin.isEnabled())
      throw new APIException("The plugin is not enable!"); 
    iHDeveloperAPI.plugin = plugin;
    getPlugin().getConfig().options().copyDefaults();
    getPlugin().saveDefaultConfig();
    loginEvent = new LoginEvent();
    joinEvent = new JoinEvent();
    interactEvent = new InteractEvent();
    kickEvent = new KickEvent();
    quitEvent = new QuitEvent();
    pingEvent = new PingEvent();
    creatureEvent = new CreatureEvent();
    inventoryEvents = new InventoryEvents();
    deathEvent = new DeathEvent();
    damageEvent = new DamageEvent();
    foodChangeEvent = new FoodChangeEvent();
    blockEvents = new BlockEvents();
    worldChangeEvent = new PlayerWorldChangeEvent();
    uuids = new UUIDDatabase();
    players = new HashMap<>();
    getServer().getMessenger().registerOutgoingPluginChannel((Plugin)getPlugin(), "BungeeCord");
    loadSettings();
    registerDefaultEvents();
    loadPlayers();
    PermissionsManager.load();
    loadCommands();
  }
  
  private static void loadSettings() {
    prefix = getConfig().getString("settings.general.prefix");
  }
  
  private static void registerDefaultEvents() {
    PluginManager pm = getPluginManager();
    pm.registerEvents(loginEvent, (Plugin)getPlugin());
    pm.registerEvents(joinEvent, (Plugin)getPlugin());
    pm.registerEvents(interactEvent, (Plugin)getPlugin());
    pm.registerEvents(kickEvent, (Plugin)getPlugin());
    pm.registerEvents(quitEvent, (Plugin)getPlugin());
    pm.registerEvents(pingEvent, (Plugin)getPlugin());
    pm.registerEvents(creatureEvent, (Plugin)getPlugin());
    pm.registerEvents(inventoryEvents, (Plugin)getPlugin());
    pm.registerEvents(deathEvent, (Plugin)getPlugin());
    pm.registerEvents(damageEvent, (Plugin)getPlugin());
    pm.registerEvents(foodChangeEvent, (Plugin)getPlugin());
    pm.registerEvents(blockEvents, (Plugin)getPlugin());
    pm.registerEvents(worldChangeEvent, (Plugin)getPlugin());
  }
  
  private static void loadPlayers() {
    for (Player player : getServer().getOnlinePlayers()) {
      try {
        loadPlayer(player);
      } catch (Exception ex) {
        ex.printStackTrace();
        Debug.error("Invaild to load player %s", player.getName());
      } 
    } 
  }
  
  public static void loadPlayer(Player player) throws APIException {
    HDPlayer p = new HDPlayer(player);
    players.put(p.getUUID(), p);
    Debug.log("[%s] Loaded as %s", p.getName(), p.getUUID());
  }
  
  private static void loadCommands() {
    try {
      CommandManager.addCommand(new IssueCommand());
      CommandManager.addCommand(new HologramCommand());
    } catch (APIException e) {
      e.printStackTrace();
    } 
  }
  
  public synchronized void setPrefix(String prefix) {
    iHDeveloperAPI.prefix = prefix;
    Debug.log("The prefix has been changed to " + prefix);
  }
  
  public static File getDataFolder() {
    return getPlugin().getDataFolder();
  }
  
  public static PluginManager getPluginManager() {
    return getServer().getPluginManager();
  }
  
  public static PluginCommand getCommand(String name) {
    return getPlugin().getCommand(name);
  }
  
  public static Configuration getConfig() {
    return (Configuration)getPlugin().getConfig();
  }
  
  public static JavaPlugin getPlugin() {
    return plugin;
  }
  
  public static UUIDDatabase getUUIDDatabase() {
    return uuids;
  }
  
  public static Server getServer() {
    return getPlugin().getServer();
  }

  public static HDPlayer getPlayer(String name) {
    UUID uuid = null;
    try {
      uuid = getUUIDDatabase().get(name);
    } catch (NullPointerException exception) {
      exception.printStackTrace();
    }
    if (uuid == null)
      return null; 
    return getPlayer(uuid);
  }
  
  public static HDPlayer getPlayer(UUID uuid) {
    if (uuid == null)
      return null; 
    try {
      return players.get(uuid);
    } catch (NullPointerException nullPointerException) {
      return null;
    } 
  }
  
  public static String color(String format, Object... args) {
    return ChatColor.translateAlternateColorCodes('&', String.format(format, args));
  }
  
  public static List<HDPlayer> getPlayers() {
    List<HDPlayer> list = new ArrayList<>();
    for (Player p : getServer().getOnlinePlayers()) {
      HDPlayer pClass = getPlayer(p.getName());
      list.add(pClass);
    } 
    return list;
  }
  
  public static String getPrefix() {
    return prefix;
  }
  
  public static int[] getItemData(String code) {
    int[] abc = new int[2];
    int id = -1;
    int damage = 0;
    int a = code.indexOf(":");
    if (a != -1) {
      id = Integer.parseInt(code.substring(0, a));
      damage = Integer.parseInt(code.substring(a + 1, code.length()));
    } 
    id = Integer.parseInt(code);
    abc[0] = id;
    abc[1] = damage;
    Debug.log("ItemData = [id='" + abc[0] + "',damage='" + abc[1] + "',data='" + code + "']", new Object[0]);
    return abc;
  }
  
  public static String format(String message, String[] keys, String[] values) {
    if (keys.length != values.length)
      return message; 
    String a = message;
    for (int i = 0; i < keys.length; i++) {
      String key = keys[i];
      String value = values[i];
      a = a.replaceAll("%" + key + "%", value);
    } 
    a = color(a);
    return a;
  }
}
