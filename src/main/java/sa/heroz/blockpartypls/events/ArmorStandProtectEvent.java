package sa.heroz.blockpartypls.events;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import sa.heroz.blockpartypls.until.TempSettings;

public class ArmorStandProtectEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
    HDPlayer HDPlayer = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    if (!e.getRightClicked().getType().equals(EntityType.ARMOR_STAND))
      return; 
    if (TempSettings.build && HDPlayer.getPlayer().isOp()) {
      e.setCancelled(false);
      return;
    } 
    e.setCancelled(true);
  }
}
