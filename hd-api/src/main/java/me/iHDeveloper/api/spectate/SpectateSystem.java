package me.iHDeveloper.api.spectate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.spectate.ui.BackToHubFolder;
import me.iHDeveloper.api.spectate.ui.CompassFolder;
import me.iHDeveloper.api.spectate.ui.PlayAgainFolder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class SpectateSystem {
  private static final Map<UUID, HDPlayer> players = new HashMap<>();
  
  public static void addPlayer(HDPlayer HDPlayer) {
    players.put(HDPlayer.getUUID(), HDPlayer);
    HDPlayer.clearInv();
    HDPlayer.addItem(0, new CompassFolder());
    HDPlayer.addItem(7, new PlayAgainFolder());
    HDPlayer.addItem(8, new BackToHubFolder());
    HDPlayer.setGameMode(GameMode.ADVENTURE);
    HDPlayer.getPlayer().setAllowFlight(true);
    HDPlayer.getPlayer().setFallDistance(0.0F);
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (!is(iHDeveloperAPI.getPlayer(p.getName())))
        p.hidePlayer(HDPlayer.getPlayer());
    } 
    Debug.log("[/%s] Added on spectate system", HDPlayer.getName());
  }
  
  public static void removePlayer(HDPlayer HDPlayer) {
    if (!is(HDPlayer))
      return; 
    players.put(HDPlayer.getUUID(), null);
    HDPlayer.clearInv();
    HDPlayer.getPlayer().setAllowFlight(false);
    HDPlayer.setGameMode(GameMode.ADVENTURE);
    for (Player p : Bukkit.getOnlinePlayers())
      p.showPlayer(HDPlayer.getPlayer());
    Debug.log("[/%s] Removed on spectate system", HDPlayer.getName());
  }
  
  public static boolean is(HDPlayer HDPlayer) {
    boolean b;
    try {
      HDPlayer p = players.get(HDPlayer.getUUID());
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
      HDPlayer HDPlayer = players.get(uuid);
      if (HDPlayer != null)
        removePlayer(HDPlayer);
    } 
    Debug.log("SpectateSystem has been reseted!");
  }
}
