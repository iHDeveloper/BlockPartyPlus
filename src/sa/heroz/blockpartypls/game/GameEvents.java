package sa.heroz.blockpartypls.game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class GameEvents {
  private static final Map<UUID, UUID> players = new HashMap<>();
  
  public static void onDamage(EntityDamageByEntityEvent e) {
    Player player = iHDeveloperAPI.getPlayer(e.getEntity().getName());
    Player damager = iHDeveloperAPI.getPlayer(e.getDamager().getName());
    players.put(player.getUUID(), damager.getUUID());
  }
  
  public static Player getDamager(UUID uuid) {
    Player damager = null;
    try {
      damager = iHDeveloperAPI.getPlayer(players.get(uuid));
    } catch (NullPointerException ex) {
      damager = null;
    } 
    return damager;
  }
  
  public static void onEliminateSpawnRIPEntity() {}
}
