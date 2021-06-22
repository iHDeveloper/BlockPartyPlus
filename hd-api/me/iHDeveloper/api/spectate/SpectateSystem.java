package me.iHDeveloper.api.spectate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import me.iHDeveloper.api.spectate.ui.BackToHubFolder;
import me.iHDeveloper.api.spectate.ui.CompassFolder;
import me.iHDeveloper.api.spectate.ui.PlayAgainFolder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class SpectateSystem {
  private static Map<UUID, Player> players = new HashMap<>();
  
  public static void addPlayer(Player player) {
    players.put(player.getUUID(), player);
    player.clearInv();
    player.addItem(0, new CompassFolder());
    player.addItem(7, new PlayAgainFolder());
    player.addItem(8, new BackToHubFolder());
    player.setGameMode(GameMode.ADVENTURE);
    player.getPlayer().setAllowFlight(true);
    player.getPlayer().setFallDistance(0.0F);
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (!is(iHDeveloperAPI.getPlayer(p.getName())))
        p.hidePlayer(player.getPlayer()); 
    } 
    Debug.log("[/%s] Added on spectate system", new Object[] { player.getName() });
  }
  
  public static void removePlayer(Player player) {
    if (!is(player))
      return; 
    players.put(player.getUUID(), null);
    player.clearInv();
    player.getPlayer().setAllowFlight(false);
    player.setGameMode(GameMode.ADVENTURE);
    for (Player p : Bukkit.getOnlinePlayers())
      p.showPlayer(player.getPlayer()); 
    Debug.log("[/%s] Removed on spectate system", new Object[] { player.getName() });
  }
  
  public static boolean is(Player player) {
    boolean b = false;
    try {
      Player p = players.get(player.getUUID());
      if (p == null)
        return false; 
      b = true;
    } catch (NullPointerException ex) {
      return false;
    } 
    return b;
  }
  
  public static void reset() {
    for (UUID uuid : players.keySet()) {
      Player player = players.get(uuid);
      if (player != null)
        removePlayer(player); 
    } 
    Debug.log("SpectateSystem has been reseted!", new Object[0]);
  }
}
