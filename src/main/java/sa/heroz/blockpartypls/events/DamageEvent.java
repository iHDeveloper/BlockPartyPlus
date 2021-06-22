package sa.heroz.blockpartypls.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import sa.heroz.blockpartypls.game.GameEvents;
import sa.heroz.blockpartypls.until.TempSettings;

public class DamageEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onDamage(EntityDamageEvent e) {
    if (!TempSettings.build) {
      if (e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
        e.setCancelled(true);
        return;
      } 
    } else {
      e.setCancelled(false);
      if (e instanceof org.bukkit.entity.Player)
        e.setCancelled(true); 
      return;
    } 
    if (!(e instanceof org.bukkit.entity.Player)) {
      e.setCancelled(true);
      return;
    } 
    if (e instanceof EntityDamageByEntityEvent) {
      GameEvents.onDamage((EntityDamageByEntityEvent)e);
      e.setCancelled(TempSettings.pvp);
      return;
    } 
    e.setCancelled(true);
  }
}
