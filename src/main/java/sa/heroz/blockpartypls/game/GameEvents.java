package sa.heroz.blockpartypls.game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class GameEvents {
  private static final Map<UUID, UUID> players = new HashMap<>();
  
  public static void onDamage(EntityDamageByEntityEvent e) {
    HDPlayer HDPlayer = iHDeveloperAPI.getPlayer(e.getEntity().getName());
    HDPlayer damager = iHDeveloperAPI.getPlayer(e.getDamager().getName());
    players.put(HDPlayer.getUUID(), damager.getUUID());
  }
  
  public static HDPlayer getDamager(UUID uuid) {
    HDPlayer damager = null;
    try {
      damager = iHDeveloperAPI.getPlayer(players.get(uuid));
    } catch (NullPointerException ex) {
      damager = null;
    } 
    return damager;
  }
  
  public static void onEliminateSpawnRIPEntity() {}
}
